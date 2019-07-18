package com.allinpay.core.exception;

/**
 * 自定义异常，用于描述业务中出现的异常
 */
public class AllinpayException extends RuntimeException {
    /**
     * 错误编码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 构造一个基本异常.
     *
     * @param message 信息描述
     */
    public AllinpayException(String message) {
        super(message);
        this.errorMsg = message;
    }

    /**
     * 构造一个基本异常.
     *
     * @param errorCode 错误编码
     * @param message   信息描述
     */
    public AllinpayException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.errorMsg = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
