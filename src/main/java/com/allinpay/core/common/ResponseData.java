package com.allinpay.core.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ResponseData<T> {
    private String code;
    private String msg;
    private T data;

    private ResponseData(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResponseData success() {
        return new ResponseData("00000", "success");
    }

    public static ResponseData success(String message) {
        return new ResponseData("00000", message);
    }

    public static ResponseData failure(String code, String message) {
        return new ResponseData(code, message);
    }

    public ResponseData<T> setData(T data) {
        this.data = data;
        return this;
    }
}
