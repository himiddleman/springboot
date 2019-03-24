package com.allinpay.core.handler;

import com.allinpay.core.common.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局异常处理器
 */
@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseData missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
        logger.info("参数缺失", e);
        return new ResponseData().failure(null);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseData illegalArgumentExceptionHandler(IllegalArgumentException e) {
        logger.info("参数不合法", e);
        return new ResponseData().failure(null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseData exceptionHandler(Exception e) {
        logger.info("系统异常", e);
        return new ResponseData().failure(null);
    }
}
