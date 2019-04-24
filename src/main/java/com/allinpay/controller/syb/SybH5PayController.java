package com.allinpay.controller.syb;

import com.allinpay.core.constant.syb.SybConstant;
import com.allinpay.core.util.syb.HttpConnectionUtil;
import com.allinpay.core.util.syb.MD5Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.TreeMap;

/**
 * 收银宝H5支付
 */
@RequestMapping("/syb/h5")
@Controller
public class SybH5PayController {
    @RequestMapping("/pay")
    public void getQRCode(String param, String merchantId, HttpServletResponse response) throws Exception {
        System.out.println(merchantId + ":" + param);
        HttpConnectionUtil connection = new HttpConnectionUtil(SybConstant.H5_URL + "unionorder");
        connection.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("appid", SybConstant.APPID);
        //通联分配的二维码编码
        params.put("cusid", SybConstant.CUSID);
        params.put("version", "12");
        params.put("trxamt", "1");
        params.put("reqsn", "shlh110224");
        params.put("charset", "utf-8");
        params.put("returl", "www.baidu.com");
        params.put("notify_url", "www.baidu.com");
        params.put("body", "谭光");
        //上限：150个字符
        params.put("remark", param);
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("sign", MD5Util.sign(params, SybConstant.APPKEY));
        byte[] bytes = connection.postParams(params, true);
        String result = new String(bytes, "utf-8");
        System.out.println(result);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print(result);
    }

    @RequestMapping("/query")
    public void query(HttpServletResponse response) throws Exception {
        HttpConnectionUtil connection = new HttpConnectionUtil(SybConstant.SYB_APIURL + "/query");
        connection.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("appid", SybConstant.APPID);
        //通联分配的二维码编码
        params.put("cusid", SybConstant.CUSID);
        params.put("version", "12");
        params.put("reqsn", "shlh110222");
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("sign", MD5Util.sign(params, SybConstant.APPKEY));
        byte[] bytes = connection.postParams(params, true);
        String result = new String(bytes, "utf-8");
        System.out.println(result);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print(result);
    }

    @RequestMapping("/cancel")
    public void cancel(HttpServletResponse response) throws Exception {
        HttpConnectionUtil connection = new HttpConnectionUtil(SybConstant.SYB_APIURL + "/cancel");
        connection.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("appid", SybConstant.APPID);
        //通联分配的二维码编码
        params.put("cusid", SybConstant.CUSID);
        params.put("version", "12");
        params.put("reqsn", System.currentTimeMillis() + "");
        params.put("oldreqsn", "shlh110223");
        params.put("trxamt", "1");
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("sign", MD5Util.sign(params, SybConstant.APPKEY));
        byte[] bytes = connection.postParams(params, true);
        String result = new String(bytes, "utf-8");
        System.out.println(result);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print(result);
    }
}
