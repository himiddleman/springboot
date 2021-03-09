package com.allinpay.controller.ali;

import lombok.Data;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * author: tanguang
 * data: 2021/3/5
 * 卫士通加密机
 **/
@Data
public abstract class HSMCommand {
    // 错误码
    public int errorCode;
    //状态码
    private int statusCode;

    // 封装消息报文
    public abstract void packetInputField(OutputStream os) throws Exception;

    // 解析响应报文
    public boolean parseOutputField(InputStream is) throws Exception {
        // 应答码
        statusCode = is.read();
        if ('E' == (char) statusCode) {
            errorCode = is.read();
            parseErrorCode(errorCode);
            return false;
        }
        return true;
    }

    public abstract void parseErrorCode(int errorCode);
}
