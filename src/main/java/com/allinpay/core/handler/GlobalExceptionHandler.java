package com.allinpay.core.handler;

import com.allinpay.core.common.ResponseData;
import com.allinpay.core.constant.enums.BizEnums;
import com.allinpay.core.exception.AllinpayException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局异常处理器
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public ResponseData missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
        log.info("参数缺失", e);
        return ResponseData.failure(BizEnums.MISSING_PARAM.getCode(), BizEnums.MISSING_PARAM.getMsg());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseData illegalArgumentExceptionHandler(IllegalArgumentException e) {
        log.info("参数不合法", e);
        return ResponseData.failure(BizEnums.ILLEGAL_ARGUMENT.getCode(), BizEnums.ILLEGAL_ARGUMENT.getMsg());
    }

    @ExceptionHandler(AllinpayException.class)
    @ResponseBody
    public ResponseData allinpayExceptionHandler(AllinpayException e) {
        return ResponseData.failure(e.getErrorCode(), e.getErrorMsg());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public String unauthorizedExceptionHandler(UnauthorizedException e) {
        return "common/403";
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseData exceptionHandler(Exception e) {
        log.info("系统异常", e);
        return ResponseData.failure(BizEnums.EXCEPTION.getCode(), BizEnums.EXCEPTION.getMsg());
    }
}
