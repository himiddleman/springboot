package com.allinpay.controller;

import com.allinpay.core.common.ResponseData;
import com.allinpay.service.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 订单增量数据采集
 * 40M=41943040B
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class FileController {
    @Autowired
    private IFileService fileService;

    @PostMapping("/collect")
    public ResponseData collectData(HttpServletRequest request) {
        log.info("上传文件个数：{}", 1);
        Map<String, String> resultMap = fileService.doCollect(request);
        return new ResponseData().success(resultMap);
    }

    @RequestMapping("/sync")
    public ResponseData sync() {
        log.info("数据同步！");
        return null;
    }
}
