package com.allinpay.controller.syb;

import com.allinpay.core.constant.syb.SybConstant;
import com.allinpay.core.util.syb.HttpConnectionUtil;
import com.allinpay.core.util.syb.MD5Util;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.TreeMap;

@RequestMapping("/syb/prepay")
@RestController
public class SybPrepayController {
    public static void main(String[] args) throws Exception {
        //支付
//        HttpConnectionUtil http = new HttpConnectionUtil(SybConstant.PREPAY_URL + "/pay");
//        http.init();
//        TreeMap<String, String> params = new TreeMap<>();
//        params.put("cusid", SybConstant.PREPAY_CUSID);
//        params.put("appid", SybConstant.PREPAY_APPID);
//        params.put("version", "11");
//        params.put("trxamt", "2");
//        params.put("reqsn", "C212098908");
//        params.put("paytype", "W01");
//        params.put("body", "网上收银测试");
//        params.put("randomstr", System.currentTimeMillis() + "");
//        params.put("sign", MD5Util.sign(params, SybConstant.PREPAY_APPKEY));
//        byte[] bys = http.postParams(params, true);
//        String result = new String(bys, "UTF-8");
//        System.out.println(result);
        //查询
//        HttpConnectionUtil http = new HttpConnectionUtil(SybConstant.PREPAY_URL + "/query");
//        http.init();
//        TreeMap<String, String> params = new TreeMap<>();
//        params.put("cusid", SybConstant.PREPAY_CUSID);
//        params.put("appid", SybConstant.PREPAY_APPID);
//        params.put("version", "11");
//        params.put("reqsn", "C112098908");
//        params.put("randomstr", System.currentTimeMillis() + "");
//        params.put("sign", MD5Util.sign(params, SybConstant.PREPAY_APPKEY));
//        byte[] bys = http.postParams(params, true);
//        String result = new String(bys, "UTF-8");
//        System.out.println(result);
        //预消费完成
//        HttpConnectionUtil http = new HttpConnectionUtil(SybConstant.PREPAY_URL + "/finish");
//        http.init();
//        TreeMap<String, String> params = new TreeMap<>();
//        params.put("cusid", SybConstant.PREPAY_CUSID);
//        params.put("appid", SybConstant.PREPAY_APPID);
//        params.put("version", "11");
//        params.put("trxamt", "1");
//        params.put("reqsn", "C11209890801");
//        params.put("oldtrxid", "112094120001112255");
//        params.put("randomstr", System.currentTimeMillis() + "");
//        params.put("sign", MD5Util.sign(params, SybConstant.PREPAY_APPKEY));
//        byte[] bys = http.postParams(params, true);
//        String result = new String(bys, "UTF-8");
//        System.out.println(result);
        //退款
//        HttpConnectionUtil http = new HttpConnectionUtil(SybConstant.PREPAY_URL + "/refund");
//        http.init();
//        TreeMap<String, String> params = new TreeMap<>();
//        params.put("cusid", SybConstant.PREPAY_CUSID);
//        params.put("appid", SybConstant.PREPAY_APPID);
//        params.put("version", "11");
//        params.put("trxamt", "1");
//        params.put("reqsn", "C11209890802");
//        params.put("oldtrxid", "112094120001112261");
//        params.put("randomstr", System.currentTimeMillis() + "");
//        params.put("sign", MD5Util.sign(params, SybConstant.PREPAY_APPKEY));
//        byte[] bys = http.postParams(params, true);
//        String result = new String(bys, "UTF-8");
//        System.out.println(result);
        //撤销
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstant.PREPAY_URL + "/cancel");
        http.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("cusid", SybConstant.PREPAY_CUSID);
        params.put("appid", SybConstant.PREPAY_APPID);
        params.put("version", "11");
        params.put("trxamt", "2");
        params.put("reqsn", "C21209890801");
        params.put("oldtrxid", "112094120001112269");
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("sign", MD5Util.sign(params, SybConstant.PREPAY_APPKEY));
        byte[] bys = http.postParams(params, true);
        String result = new String(bys, "UTF-8");
        System.out.println(result);
    }

    @RequestMapping("/pay")
    public String pay() throws Exception {
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstant.PREPAY_URL + "/pay");
        http.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("cusid", SybConstant.PREPAY_CUSID);
        params.put("appid", SybConstant.PREPAY_APPID);
        params.put("version", "11");
        params.put("trxamt", "1");
        params.put("reqsn", "C112098908");
        params.put("paytype", "W01");
        params.put("body", "网上收银测试");
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("sign", MD5Util.sign(params, SybConstant.PREPAY_APPKEY));
        byte[] bys = http.postParams(params, true);
        String result = new String(bys, "UTF-8");
        doResult(result);
        System.out.println(result);
        return result;
    }

    @RequestMapping("/finish")
    public String finish() throws Exception {
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstant.PREPAY_URL + "/finish");
        http.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("cusid", SybConstant.PREPAY_CUSID);
        params.put("appid", SybConstant.PREPAY_APPID);
        params.put("version", "11");
        params.put("trxamt", "1");
        params.put("reqsn", "C112098908");
        params.put("oldtrxid", "");
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("sign", MD5Util.sign(params, SybConstant.PREPAY_APPKEY));
        byte[] bys = http.postParams(params, true);
        String result = new String(bys, "UTF-8");
        System.out.println(result);
        return result;
    }

    @RequestMapping("/cancel")
    public String cancel() throws Exception {
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstant.PREPAY_URL + "/cancel");
        http.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("cusid", SybConstant.PREPAY_CUSID);
        params.put("appid", SybConstant.PREPAY_APPID);
        params.put("version", "11");
        params.put("trxamt", "1");
        params.put("reqsn", "C112098908");
        params.put("oldtrxid", "");
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("sign", MD5Util.sign(params, SybConstant.PREPAY_APPKEY));
        byte[] bys = http.postParams(params, true);
        String result = new String(bys, "UTF-8");
        System.out.println(result);
        return result;
    }

    @RequestMapping("/refund")
    public String refund() throws Exception {
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstant.PREPAY_URL + "/refund");
        http.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("cusid", SybConstant.PREPAY_CUSID);
        params.put("appid", SybConstant.PREPAY_APPID);
        params.put("version", "11");
        params.put("trxamt", "1");
        params.put("reqsn", "C112098908");
        params.put("oldtrxid", "");
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("sign", MD5Util.sign(params, SybConstant.PREPAY_APPKEY));
        byte[] bys = http.postParams(params, true);
        String result = new String(bys, "UTF-8");
        System.out.println(result);
        return result;
    }

    @RequestMapping("/query")
    public String query() throws Exception {
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstant.PREPAY_URL + "/query");
        http.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("cusid", SybConstant.PREPAY_CUSID);
        params.put("appid", SybConstant.PREPAY_APPID);
        params.put("version", "11");
        params.put("trxamt", "1");
        params.put("reqsn", "C112098908");
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("sign", MD5Util.sign(params, SybConstant.PREPAY_APPKEY));
        byte[] bys = http.postParams(params, true);
        String result = new String(bys, "UTF-8");
        System.out.println(result);
        return result;
    }

    private void doResult(String result) throws Exception {
        TreeMap treeMap = MD5Util.json2Obj(result, TreeMap.class);
        System.out.println("验签结果：" + MD5Util.validSign(treeMap, SybConstant.PREPAY_APPKEY));
    }
}
