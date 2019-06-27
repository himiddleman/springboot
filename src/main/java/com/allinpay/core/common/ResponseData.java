package com.allinpay.core.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class ResponseData<T> {
    private String code;
    private String msg;
    private T data;
    private int count;

    public ResponseData success(T data) {
        this.code = "200";
        this.msg = "成功";
        this.data = data;
        if (data instanceof List) {
//            this.count = ((List) data).size();
            //测试
            this.count = 7;
        } else {
            this.count = 1;
        }
        return this;
    }

    public ResponseData failure(T data) {
        this.code = "500";
        this.msg = "失败";
        this.data = data;
        return this;
    }
}
