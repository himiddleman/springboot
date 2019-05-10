package com.allinpay.controller.syb;

import com.allinpay.core.constant.syb.SybConstant;
import com.allinpay.core.util.syb.HttpConnectionUtil;
import com.allinpay.core.util.syb.MD5Util;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.TreeMap;

@RequestMapping("/syb/online")
@RestController
public class SybOnlinePayController {
    @RequestMapping("/pay")
    public Map<String, String> pay() throws Exception {
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstant.SYB_APIURL + "/pay");
        http.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("cusid", SybConstant.SYB_CUSID);
        params.put("appid", SybConstant.SYB_APPID);
        params.put("version", "11");
        params.put("trxamt", "1");
        params.put("reqsn", "shlh110330");
        params.put("paytype", "W01");
        params.put("body", "网上收银测试");
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("sign", MD5Util.sign(params, SybConstant.SYB_APPKEY));
        byte[] bys = http.postParams(params, true);
        String result = new String(bys, "UTF-8");
        Map<String, String> map = handleResult(result);
        return map;
    }

    @RequestMapping("/scanpay")
    public Map<String, String> scanpay() throws Exception {
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstant.SYB_APIURL + "/scanqrpay");
        http.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("cusid", SybConstant.SYB_CUSID);
        params.put("appid", SybConstant.SYB_APPID);
        params.put("version", "11");
        params.put("trxamt", "1");
        params.put("reqsn", "HU20190509105122170219");
        params.put("authcode", "134843346364067624");
//        params.put("paytype", "W04");
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("remark", "bosinyPay");
        params.put("sign", MD5Util.sign(params, SybConstant.SYB_APPKEY));
        byte[] bys = http.postParams(params, true);
        String result = new String(bys, "UTF-8");
        Map<String, String> map = handleResult(result);
        return map;
    }

    @RequestMapping("/h5pay")
    public Map<String, String> h5pay() throws Exception {
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstant.SYB_APIURL + "/h5pay");
        http.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("cusid", SybConstant.SYB_CUSID);
        params.put("appid", SybConstant.SYB_APPID);
        params.put("version", "11");
        params.put("trxamt", "1");
        params.put("reqsn", "shlh110113");
        params.put("paytype", "W03");
        params.put("apptype", "Wap");
        params.put("appname", "收银宝");
        params.put("apppackage", "www.baidu.com");
        params.put("cusip", "10.215.0.38");
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("sign", MD5Util.sign(params, SybConstant.SYB_APPKEY));
        byte[] bys = http.postParams(params, true);
        String result = new String(bys, "UTF-8");
        Map<String, String> map = handleResult(result);
        return map;
    }

    @RequestMapping("/cancel")
    public Map<String, String> cancel() throws Exception {
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstant.SYB_APIURL + "/cancel");
        http.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("cusid", SybConstant.SYB_CUSID);
        params.put("appid", SybConstant.SYB_APPID);
        params.put("version", "11");
        params.put("trxamt", "1");
        params.put("reqsn", "12345678901");
        params.put("oldtrxid", "111994120000763381");
        params.put("oldreqsn", "HU20190509105122170219");
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("sign", MD5Util.sign(params, SybConstant.SYB_APPKEY));
        byte[] bys = http.postParams(params, true);
        String result = new String(bys, "UTF-8");
        Map<String, String> map = handleResult(result);
        return map;
    }

    @RequestMapping("/refund")
    public Map<String, String> refund() throws Exception {
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstant.SYB_APIURL + "/refund");
        http.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("cusid", SybConstant.SYB_CUSID);
        params.put("appid", SybConstant.SYB_APPID);
        params.put("version", "11");
        params.put("trxamt", "1");
        params.put("reqsn", "1234567890");
        params.put("oldreqsn", "HU20190509105122170219");
        params.put("oldtrxid", "111994120000763381");
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("sign", MD5Util.sign(params, SybConstant.SYB_APPKEY));
        byte[] bys = http.postParams(params, true);
        String result = new String(bys, "UTF-8");
        Map<String, String> map = handleResult(result);
        return map;
    }

    @RequestMapping("/query")
    public Map<String, String> query() throws Exception {
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstant.SYB_APIURL + "/query");
        http.init();
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("cusid", SybConstant.SYB_CUSID);
        params.put("appid", SybConstant.SYB_APPID);
        params.put("version", "11");
        params.put("reqsn", "HU20190509105122170219");
//        params.put("trxid", "111994120000763301");
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("sign", MD5Util.sign(params, SybConstant.SYB_APPKEY));
        byte[] bys = http.postParams(params, true);
        String result = new String(bys, "UTF-8");
        Map<String, String> map = handleResult(result);
        return map;
    }


    public static Map<String, String> handleResult(String result) throws Exception {
        Map map = MD5Util.json2Obj(result, Map.class);
        if (map == null) {
            throw new Exception("返回数据错误");
        }
        if ("SUCCESS".equals(map.get("retcode"))) {
            TreeMap tmap = new TreeMap();
            tmap.putAll(map);
            String sign = tmap.remove("sign").toString();
            String sign1 = MD5Util.sign(tmap, SybConstant.SYB_APPKEY);
            if (sign1.toLowerCase().equals(sign.toLowerCase())) {
                return map;
            } else {
                throw new Exception("验证签名失败");
            }

        } else {
            throw new Exception(map.get("retmsg").toString());
        }
    }

    public void dealProcess() throws Exception {
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstant.SYB_APIURL + "/query");
        http.init();
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("cusid", SybConstant.SYB_CUSID);
        params.put("appid", SybConstant.SYB_APPID);
        params.put("version", "11");
        params.put("reqsn", "HU20190509105122170219");
//        params.put("trxid", "111994120000763301");
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("sign", MD5Util.sign(params, SybConstant.SYB_APPKEY));
        byte[] bys = http.postParams(params, true);
        String result = new String(bys, "UTF-8");
        System.out.println(result);
    }
}
