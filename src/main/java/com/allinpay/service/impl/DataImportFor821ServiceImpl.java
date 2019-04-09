package com.allinpay.service.impl;

import com.allinpay.repository.domain.TradeFor821;
import com.allinpay.service.IDataImportFor821Service;
import com.csvreader.CsvReader;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DataImportFor821ServiceImpl implements IDataImportFor821Service {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    //避免并发访问，统计插入的记录数
    private int count;

    /**
     * @param path
     */
    @Override
    public Map<String, String> importData(String path) {
        //将目标文件放入集合中
        List<File> targetFileList = new ArrayList<>();
        //操作结果
        Map<String, String> resultMap = new HashMap<>();
        //文件的开始路径
        File startFilePath = new File(path);
        loopDirectory(startFilePath, targetFileList);
        log.info("需要导入的文件数量为：{}", targetFileList.size());
        //文件导入操作
        for (File targetFile : targetFileList) {
            doImport(targetFile, resultMap);
        }
        log.info("文件导入结束！总共插入的记录数为：{}", count);
        return resultMap;
    }

    /**
     * 递归查找指定的文件
     *
     * @param targetFile
     * @param targetFileList
     */
    private void loopDirectory(File targetFile, List targetFileList) {
        if (targetFile.isFile()) {
            if (targetFile.getName().startsWith("txn")) {
                targetFileList.add(targetFile);
            }
            return;
        }
        if (targetFile.getName().startsWith("2017")) {
            return;
        }
        for (File file : targetFile.listFiles()) {
            loopDirectory(file, targetFileList);
        }
    }

    /**
     * 文件导入操作
     * System.out.println(csvReader.get("Link"))
     *
     * @param targetFile
     */
    private void doImport(File targetFile, Map<String, String> resultMap) {
        List<TradeFor821> resultList = new ArrayList<>();
        try {
            // 创建CSV读对象
            CsvReader csvReader = new CsvReader(new FileInputStream(targetFile), Charset.forName("GBK"));
            // 读表头
            csvReader.readHeaders();
            Class clazz = TradeFor821.class;
            while (csvReader.readRecord()) {
                // 读一整行
                String formatStr = csvReader.getRawRecord().replaceAll("\"", "");
                String[] rowArray = formatStr.split(",");
                //讲读取到的字符创封装到实体类中，讲实体类放入集合中
                TradeFor821 tradeFor821 = (TradeFor821) clazz.newInstance();
                for (int i = 0; i < rowArray.length; i++) {
                    Field[] fields = clazz.getDeclaredFields();
                    PropertyDescriptor pd = new PropertyDescriptor(fields[i].getName(), clazz);
                    Method setMethod = pd.getWriteMethod();
                    setMethod.invoke(tradeFor821, rowArray[i]);
                }
                resultList.add(tradeFor821);
            }
            count += resultList.size();
            doBatchInsert(resultList, resultMap, targetFile);
        } catch (Exception e) {
            log.error("文件读取失败！", e);
            resultMap.put(targetFile.getName(), "读csv文件出现异常，请重新上传该文件！");
        }
    }

    /**
     * 批量插入操作
     * 集合中的实体信息插入到临时表中，对于一个文件在插入过程中出现异常时，整个文件的插入操作都将回滚
     *
     * @param resultList
     * @param resultMap
     * @param targetFile
     */
    private void doBatchInsert(List<TradeFor821> resultList, Map<String, String> resultMap, File targetFile) {
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        int batchCount = 100;// 每批commit的个数
        int batchLastIndex = batchCount;// 每批最后一个的下标
        try {
            for (int index = 0; index < resultList.size(); ) {
                if (batchLastIndex >= resultList.size()) {
                    batchLastIndex = resultList.size();
                    session.insert("com.allinpay.repository.mapper.TradeFor821Mapper.insertBatch", resultList.subList(index, batchLastIndex));
                    // 数据插入完毕，退出循环
                    break;
                } else {
                    session.insert("com.allinpay.repository.mapper.TradeFor821Mapper.insertBatch", resultList.subList(index, batchLastIndex));
                    // 设置下一批下标
                    index = batchLastIndex;
                    batchLastIndex = index + batchCount;
                }
            }
            session.commit();
            resultMap.put(targetFile.getName(), "文件导入成功！");
        } catch (Exception e) {
            log.error("批量插入数据失败！", e);
            resultMap.put(targetFile.getName(), "批量插入数据失败，请重试！");
        } finally {
            session.close();
        }
    }
}
