package com.allinpay.core.common;

public class ResponseData<T> {
    private String code;
    private String msg;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseData success(T data) {
        this.code = "200";
        this.msg = "成功";
        this.data = data;
        return this;
    }

    public ResponseData failure(T data) {
        this.code = "500";
        this.msg = "失败";
        this.data = data;
        return this;
    }
}
