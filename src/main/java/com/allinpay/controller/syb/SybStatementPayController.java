package com.allinpay.controller.syb;

import com.allinpay.core.util.syb.HttpConnectionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.TreeMap;

/**
 * 收银宝H5支付
 */
@RequestMapping("/syb/h5")
@Controller
public class SybStatementPayController {
    public static void main(String[] args) throws Exception {
        HttpConnectionUtil connection = new HttpConnectionUtil("https://vsp.allinpay.com/cusapi/trxfile/get");
        connection.init();
        TreeMap<String, String> params = new TreeMap<>();
//        params.put("orgid", "100581048160005");
        params.put("appid", "00144427");
        params.put("cusid", "55133107278TGTU");
        params.put("date", "20200804");
        params.put("randomstr", "WxhQEvAMPA");
        params.put("sign", "DA6C3E55F337E9097ADE4BA5B0D8C3F0");
        byte[] bytes = connection.postParams(params, true);
        String result = new String(bytes, "utf-8");
        System.out.println(result);
    }
}
