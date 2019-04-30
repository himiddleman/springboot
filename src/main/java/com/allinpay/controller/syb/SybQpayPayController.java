package com.allinpay.controller.syb;

import com.allinpay.core.constant.syb.SybConstant;
import com.allinpay.core.util.syb.HttpConnectionUtil;
import com.allinpay.core.util.syb.MD5Util;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

/**
 * 收银宝H5支付
 */
@RequestMapping("/syb/qpay")
@RestController
public class SybQpayPayController {
    @RequestMapping("/agreeapply")
    public void agreeapply() throws Exception {
        HttpConnectionUtil connection = new HttpConnectionUtil(SybConstant.QUICKPAY_URL + "/agreeapply");
        connection.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("appid", SybConstant.QUICKPAY_APPID);
        params.put("cusid", SybConstant.QUICKPAY_CUSID);
        params.put("version", "11");
        params.put("reqtime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("meruserid", "11");
        params.put("accttype", "00");
        params.put("acctno", "6214680141439347");
        params.put("idno", "430221199101107119");
        params.put("acctname", "谭光");
        params.put("mobile", "15397704033");
        params.put("sign", MD5Util.sign(params, SybConstant.QUICKPAY_APPKEY));
        byte[] bytes = connection.postParams(params, true);
        String result = new String(bytes, "utf-8");
        System.out.println(result);
    }

    @RequestMapping("/agreesms")
    public void agreesms() throws Exception {
        HttpConnectionUtil connection = new HttpConnectionUtil(SybConstant.QUICKPAY_URL + "/agreesms");
        connection.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("appid", SybConstant.QUICKPAY_APPID);
        params.put("cusid", SybConstant.QUICKPAY_CUSID);
        params.put("version", "11");
        params.put("reqtime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        params.put("randomstr", System.currentTimeMillis() + "");

        params.put("meruserid", "11");
        params.put("accttype", "00");
        params.put("acctno", "6214680141439347");
        params.put("idno", "430221199101107119");
        params.put("acctname", "谭光");
        params.put("mobile", "15397704033");

        params.put("sign", MD5Util.sign(params, SybConstant.QUICKPAY_APPKEY));
        byte[] bytes = connection.postParams(params, true);
        String result = new String(bytes, "utf-8");
        System.out.println(result);
    }

    @RequestMapping("/agreeconfirm")
    public void agreeconfirm() throws Exception {
        HttpConnectionUtil connection = new HttpConnectionUtil(SybConstant.QUICKPAY_URL + "/agreeconfirm");
        connection.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("appid", SybConstant.QUICKPAY_APPID);
        params.put("cusid", SybConstant.QUICKPAY_CUSID);
        params.put("version", "11");
        params.put("reqtime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        params.put("randomstr", System.currentTimeMillis() + "");

        params.put("meruserid", "11");
        params.put("accttype", "00");
        params.put("acctno", "6214680141439347");
        params.put("idno", "430221199101107119");
        params.put("acctname", "谭光");
        params.put("mobile", "15397704033");
        params.put("smscode", "111111");

        params.put("sign", MD5Util.sign(params, SybConstant.QUICKPAY_APPKEY));
        byte[] bytes = connection.postParams(params, true);
        String result = new String(bytes, "utf-8");
        System.out.println(result);
    }

    //协议号：201904301514141853
    @RequestMapping("/payapplyagree")
    public void payapplyagree() throws Exception {
        HttpConnectionUtil connection = new HttpConnectionUtil(SybConstant.QUICKPAY_URL + "/payapplyagree");
        connection.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("appid", SybConstant.QUICKPAY_APPID);
        params.put("cusid", SybConstant.QUICKPAY_CUSID);
        params.put("version", "11");
        params.put("reqtime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        params.put("randomstr", System.currentTimeMillis() + "");

        params.put("orderid", "shlh110239");
        params.put("agreeid", "201904301514141853");
        params.put("amount", "1");
        params.put("currency", "CNY");
        params.put("subject", "支付1分钱");
        params.put("notifyurl", "www.baidu.com");

        params.put("sign", MD5Util.sign(params, SybConstant.QUICKPAY_APPKEY));
        byte[] bytes = connection.postParams(params, true);
        String result = new String(bytes, "utf-8");
        System.out.println(result);
    }

    @RequestMapping("/paysmsagree")
    public void paysmsagree() throws Exception {
        HttpConnectionUtil connection = new HttpConnectionUtil(SybConstant.QUICKPAY_URL + "/paysmsagree");
        connection.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("appid", SybConstant.QUICKPAY_APPID);
        params.put("cusid", SybConstant.QUICKPAY_CUSID);
        params.put("version", "11");
        params.put("reqtime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        params.put("randomstr", System.currentTimeMillis() + "");

        params.put("orderid", "shlh110239");
        params.put("agreeid", "201904301514141853");

        params.put("sign", MD5Util.sign(params, SybConstant.QUICKPAY_APPKEY));
        byte[] bytes = connection.postParams(params, true);
        String result = new String(bytes, "utf-8");
        System.out.println(result);
    }

    @RequestMapping("/payagreeconfirm")
    public void payagreeconfirm() throws Exception {
        HttpConnectionUtil connection = new HttpConnectionUtil(SybConstant.QUICKPAY_URL + "/payagreeconfirm");
        connection.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("appid", SybConstant.QUICKPAY_APPID);
        params.put("cusid", SybConstant.QUICKPAY_CUSID);
        params.put("version", "11");
        params.put("reqtime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        params.put("randomstr", System.currentTimeMillis() + "");

        params.put("orderid", "shlh110239");
        params.put("agreeid", "201904301514141853");
        params.put("smscode", "111111");
        String thpinfo = "{\"sign\":\"\",\"tphtrxcrtime\":\"\",\"tphtrxid\":0,\"trxflag\":\"trx\",\"trxsn\":\"\"}";
        params.put("thpinfo", thpinfo);

        params.put("sign", MD5Util.sign(params, SybConstant.QUICKPAY_APPKEY));
        byte[] bytes = connection.postParams(params, true);
        String result = new String(bytes, "utf-8");
        System.out.println(result);
    }

    @RequestMapping("/agreequery")
    public void agreequery() throws Exception {
        HttpConnectionUtil connection = new HttpConnectionUtil(SybConstant.QUICKPAY_URL + "/agreequery");
        connection.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("appid", SybConstant.QUICKPAY_APPID);
        params.put("cusid", SybConstant.QUICKPAY_CUSID);
        params.put("version", "11");
        params.put("reqtime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        params.put("randomstr", System.currentTimeMillis() + "");

        params.put("meruserid", "11");

        params.put("sign", MD5Util.sign(params, SybConstant.QUICKPAY_APPKEY));
        byte[] bytes = connection.postParams(params, true);
        String result = new String(bytes, "utf-8");
        System.out.println(result);
    }

    @RequestMapping("/unbind")
    public void unbind() throws Exception {
        HttpConnectionUtil connection = new HttpConnectionUtil(SybConstant.QUICKPAY_URL + "/unbind");
        connection.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("appid", SybConstant.QUICKPAY_APPID);
        params.put("cusid", SybConstant.QUICKPAY_CUSID);
        params.put("version", "11");
        params.put("reqtime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        params.put("randomstr", System.currentTimeMillis() + "");

        params.put("agreeid", "201904301514141853");

        params.put("sign", MD5Util.sign(params, SybConstant.QUICKPAY_APPKEY));
        byte[] bytes = connection.postParams(params, true);
        String result = new String(bytes, "utf-8");
        System.out.println(result);
    }

    @RequestMapping("/cancel")
    public void cancel() throws Exception {
        HttpConnectionUtil connection = new HttpConnectionUtil(SybConstant.QUICKPAY_URL + "/cancel");
        connection.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("appid", SybConstant.QUICKPAY_APPID);
        params.put("cusid", SybConstant.QUICKPAY_CUSID);
        params.put("version", "11");
        params.put("reqtime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        params.put("randomstr", System.currentTimeMillis() + "");

        params.put("orderid", System.currentTimeMillis() + "");
        params.put("trxamt", "1");
        params.put("oldorderid", "shlh110239");
//        params.put("oldtrxid", "111979470000270808");

        params.put("sign", MD5Util.sign(params, SybConstant.QUICKPAY_APPKEY));
        byte[] bytes = connection.postParams(params, true);
        String result = new String(bytes, "utf-8");
        System.out.println(result);
    }

    @RequestMapping("/refund")
    public void refund() throws Exception {
        HttpConnectionUtil connection = new HttpConnectionUtil(SybConstant.QUICKPAY_URL + "/refund");
        connection.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("appid", SybConstant.QUICKPAY_APPID);
        params.put("cusid", SybConstant.QUICKPAY_CUSID);
        params.put("version", "11");
        params.put("reqtime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        params.put("randomstr", System.currentTimeMillis() + "");

        params.put("orderid", System.currentTimeMillis() + "");
        params.put("trxamt", "1");
        params.put("oldorderid", "shlh110239");
//        params.put("oldtrxid", "111979470000270808");

        params.put("sign", MD5Util.sign(params, SybConstant.QUICKPAY_APPKEY));
        byte[] bytes = connection.postParams(params, true);
        String result = new String(bytes, "utf-8");
        System.out.println(result);
    }

    @RequestMapping("/query")
    public void query() throws Exception {
        HttpConnectionUtil connection = new HttpConnectionUtil(SybConstant.QUICKPAY_URL + "/query");
        connection.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("appid", SybConstant.QUICKPAY_APPID);
        params.put("cusid", SybConstant.QUICKPAY_CUSID);
        params.put("version", "11");
        params.put("reqtime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        params.put("randomstr", System.currentTimeMillis() + "");

        params.put("orderid", "shlh110239");
        params.put("trxid", "111979470000270808");

        params.put("sign", MD5Util.sign(params, SybConstant.QUICKPAY_APPKEY));
        byte[] bytes = connection.postParams(params, true);
        String result = new String(bytes, "utf-8");
        System.out.println(result);
    }
}
