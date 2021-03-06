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
    public static void main(String[] args) throws Exception {
        //订单查询
        HttpConnectionUtil connection = new HttpConnectionUtil(SybConstant.SYB_APIURL + "/query");
        connection.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("appid", "00190273");
        //通联分配的二维码编码
        params.put("cusid", "56033105399AT07");
        params.put("version", "12");
        params.put("reqsn", "shlh9231");
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("sign", MD5Util.sign(params, "allinpay888"));
        byte[] bytes = connection.postParams(params, true);
        String result = new String(bytes, "utf-8");
        System.out.println(result);

        //订单关闭
//        HttpConnectionUtil connection = new HttpConnectionUtil("https://vsp.allinpay.com/apiweb/unitorder/close");
//        connection.init();
//        TreeMap<String, String> params = new TreeMap<>();
//        params.put("cusid", "56033105399AT07");
//        params.put("appid", "00190273");
//        params.put("version", "12");
//        params.put("oldreqsn", "shlh9231");
//        params.put("signtype", "MD5");
//        params.put("randomstr", System.currentTimeMillis() + "");
//        params.put("sign", MD5Util.sign(params, "allinpay888"));
//        byte[] bytes = connection.postParams(params, true);
//        String result = new String(bytes, "utf-8");
//        System.out.println(result);

//        HttpConnectionUtil connection = new HttpConnectionUtil(SybConstant.H5_URL + "unionorder");
//        connection.init();
//        TreeMap<String, String> params = new TreeMap<>();
//        params.put("appid", "00190273");
//        //通联分配的二维码编码
//        params.put("cusid", "56033105399AT07");
//        params.put("version", "12");
//        params.put("trxamt", "1");
//        params.put("reqsn", "shlh9231");
//        params.put("charset", "utf-8");
//        params.put("returl", "https://www.baidu.com/");
//        params.put("notify_url", "http://47.99.172.60:10010/h5/policy/notify");
//        params.put("body", "谭光");
//        //上限：150个字符
////        params.put("remark", param);
//        params.put("randomstr", System.currentTimeMillis() + "");
//        params.put("sign", MD5Util.sign(params, "allinpay888"));
//        byte[] bytes = connection.postParams(params, true);
//        String result = new String(bytes, "utf-8");
//        System.out.println(result);

//        HttpConnectionUtil connection = new HttpConnectionUtil(SybConstant.SYB_APIURL + "/refund");
//        connection.init();
//        TreeMap<String, String> params = new TreeMap<>();
//        params.put("orgid", "100581048160005");
//        params.put("appid", "00003351");
//        //通联分配的二维码编码
//        params.put("cusid", "990440148166000");
//        params.put("version", "12");
//        params.put("reqsn", System.currentTimeMillis() + "");
//        params.put("oldtrxid", "112094120001139185");
//        params.put("trxamt", "1");
//        params.put("randomstr", System.currentTimeMillis() + "");
//        params.put("sign", MD5Util.sign(params, "a0ea3fa20dbd7bb4d5abf1d59d63bae8"));
//        byte[] bytes = connection.postParams(params, true);
//        String result = new String(bytes, "utf-8");
//        System.out.println(result);
    }

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
        params.put("reqsn", "shlh110333");
        params.put("charset", "utf-8");
        params.put("returl", "http://47.99.172.60:10010/h5/policy/returl");
        params.put("notify_url", "http://47.99.172.60:10010/h5/policy/notify");
        params.put("body", "谭光");
        //上限：150个字符
//        params.put("remark", param);
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
        params.put("oldreqsn", "shlh110333");
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
