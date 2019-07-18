package com.allinpay.controller.ali;

import com.allinpay.core.annotation.MyAnnotation;
import com.allinpay.core.common.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AnnotationController {
    Logger logger = LoggerFactory.getLogger(AnnotationController.class);

    /**
     * 执行方法前打印接口信息
     *
     * @return
     */
    @MyAnnotation(name = "测试注解")
    @RequestMapping("/annotation")
    public ResponseData test() {
        System.out.println("hello");
        log.info("hello");
        logger.info("hello");
        int i = 1 / 0;
        return ResponseData.success().setData("success");
    }
}
