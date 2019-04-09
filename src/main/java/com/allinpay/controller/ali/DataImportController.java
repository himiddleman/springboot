package com.allinpay.controller.ali;

import com.allinpay.core.common.ResponseData;
import com.allinpay.service.IDataImportFor821Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * oracle jar包的引入，如何安装本地已有的jar包
 * 导入821的流水数据到oracle临时表
 * 导入txn开头的文件，文件格式是csv，遍历文件
 * select * from t_trade_list_821;
 * mybatis允许插入空值时需要设置jdbcType类型
 */
@RestController
@RequestMapping("/data")
public class DataImportController {
    @Autowired
    private IDataImportFor821Service dataImportService;

    @RequestMapping("import")
    public ResponseData importData() {
        String path = "E:/821流水/舟山";
        Map<String, String> resultMap = dataImportService.importData(path);
        return new ResponseData().success(resultMap);
    }
}
