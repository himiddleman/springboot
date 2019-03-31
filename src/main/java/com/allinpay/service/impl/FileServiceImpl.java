package com.allinpay.service.impl;

import com.allinpay.repository.domain.AllinOrder;
import com.allinpay.service.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件增量更新
 */
@Service
@Slf4j
public class FileServiceImpl implements IFileService {
    //    @Autowired
//    private AllinOrderMapper allinOrderMapper;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public Map<String, String> doCollect(HttpServletRequest request) {
        long start = System.currentTimeMillis();
        MultipartHttpServletRequest mpRequest
                = (MultipartHttpServletRequest) request;
        List<MultipartFile> fileList = mpRequest.getMultiFileMap().get("upload");
        int size = fileList.size();
        //key为文件名，value为未通过验证的订单号
        Map<String, String> resultMap = new HashMap<>();
        log.info("需要处理的文件的个数：{}", size);
        //对文件进行批量处理
        if (CollectionUtils.isEmpty(fileList)) {
            log.warn("未上传文件，无需操作！");
        }
        for (int i = 0; i < fileList.size(); i++) {
            //对文件进行处理,存入数据
            dealFile(fileList.get(i), resultMap);
        }
        long end = System.currentTimeMillis();
        log.info("处理上传文件总耗时：{} min", (end - start) * 1.0 / 60000);
        return resultMap;
    }

    /**
     * 文件操作
     *
     * @param file
     * @param resultMap
     */
    private void dealFile(MultipartFile file, Map<String, String> resultMap) {
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            //校验成功的数据，存入数据库
            List<AllinOrder> resultList = new ArrayList<>();
            //对列名校验
            Sheet headSheet = workbook.getSheetAt(0);
            Row headRow = headSheet.getRow(0);
            if (!("订单号".equals(headRow.getCell(0).getStringCellValue()) &&
                    "时间".equals(headRow.getCell(1).getStringCellValue()) &&
                    "实收款".equals(headRow.getCell(6).getStringCellValue()))) {
                log.error("未找到模板标志位！");
                resultMap.put(file.getOriginalFilename(), "未找到模板标志位");
                return;
            }
            //对内容校验
            for (int i = 1; i < headSheet.getPhysicalNumberOfRows(); i++) {
                Row contentRow = headSheet.getRow(i);
                Cell orderCell = contentRow.getCell(0);
                Cell timeCell = contentRow.getCell(1);
                Cell productCell = contentRow.getCell(2);
                Cell priceCell = contentRow.getCell(3);
                Cell countCell = contentRow.getCell(4);
                Cell buyerCell = contentRow.getCell(5);
                Cell amountCell = contentRow.getCell(6);
                if (orderCell == null || amountCell == null || timeCell == null) {
                    break;
                }
                orderCell.setCellType(Cell.CELL_TYPE_STRING);
                productCell.setCellType(Cell.CELL_TYPE_STRING);
                priceCell.setCellType(Cell.CELL_TYPE_STRING);
                countCell.setCellType(Cell.CELL_TYPE_STRING);
                buyerCell.setCellType(Cell.CELL_TYPE_STRING);
                amountCell.setCellType(Cell.CELL_TYPE_STRING);
                if (StringUtils.isEmpty(orderCell.getStringCellValue()) ||
                        StringUtils.isEmpty(timeCell.getDateCellValue()) ||
                        StringUtils.isEmpty(amountCell.getStringCellValue())) {
                    log.error("订单记录信息不全！请检查第" + i + "行数据");
                    resultMap.put(file.getOriginalFilename(), "订单记录信息不全！请检查第" + i + "行数据");
                    return;
                }
                //将信息存入list集合
                AllinOrder allinOrder = new AllinOrder();
                allinOrder.setOrderNo(orderCell.getStringCellValue());
                allinOrder.setCreateTime(timeCell.getDateCellValue());
                allinOrder.setProDesc(productCell.getStringCellValue());
                allinOrder.setPrice(new BigDecimal(priceCell.getStringCellValue()));
                allinOrder.setCount(Integer.valueOf(countCell.getStringCellValue()));
                allinOrder.setBuyer(buyerCell.getStringCellValue());
                allinOrder.setAmount(new BigDecimal(amountCell.getStringCellValue()));
                resultList.add(allinOrder);
            }
            //往数据库批量新增数据
            batchInsert(resultList);
//            for (int i = 0; i < resultList.size(); i++) {
//                allinOrderMapper.insert(resultList.get(i));
//            }
            resultMap.put(file.getOriginalFilename(), "文件处理成功,文件订单个数：" + resultList.size());
        } catch (Exception e) {
            log.error("文件处理异常", e);
        }
    }


    //使用批量的方式进行插入数据 40w条 5min
    public void batchInsert(List<AllinOrder> list) {
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);
        int batchCount = 1000;// 每批commit的个数
        int batchLastIndex = batchCount;// 每批最后一个的下标
        //list 7200
        try {
            for (int index = 0; index < list.size(); ) {
                if (batchLastIndex >= list.size()) {
                    batchLastIndex = list.size();
                    session.insert("com.allinpay.repository.mapper.AllinOrderMapper.insertBatch", list.subList(index, batchLastIndex));
                    session.commit();
                    break;// 数据插入完毕，退出循环
                } else {
                    session.insert("com.allinpay.repository.mapper.AllinOrderMapper.insertBatch", list.subList(index, batchLastIndex));
                    session.commit();
                    index = batchLastIndex;// 设置下一批下标
                    batchLastIndex = index + batchCount;
                }
            }
            session.commit();
        } finally {
            session.close();
        }
    }
}
