package com.allinpay.controller.syb;

import com.allinpay.core.constant.syb.SybConstant;
import com.allinpay.core.util.syb.HttpConnectionUtil;
import com.allinpay.core.util.syb.MD5Util;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/syb")
public class SybGatewayController {

    @RequestMapping("/cancel")
    public static void cancel(long trxamt, String orderid, String trxid) throws Exception {
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstant.HOSTNAME + "/cancel");
        http.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("cusid", SybConstant.CUSID);
        params.put("appid", SybConstant.APPID);
        params.put("trxamt", String.valueOf(trxamt));
        params.put("reqsn", System.currentTimeMillis() + "");
        params.put("trxid", trxid);
        params.put("orderid", orderid);
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("sign", MD5Util.sign(params, SybConstant.APPKEY));
        byte[] bys = http.postParams(params, true);
        String result = new String(bys, "UTF-8");
        System.out.println("ret:" + result);
        Map<String, String> map = handleResult(result);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    @RequestMapping("/refund")
    public static void refund(long trxamt, String orderid, String trxid) throws Exception {
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstant.HOSTNAME + "/refund");
        http.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("cusid", SybConstant.CUSID);
        params.put("appid", SybConstant.APPID);
        params.put("trxamt", String.valueOf(trxamt));
        params.put("trxid", trxid);
        params.put("orderid", orderid);
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("reqsn", System.currentTimeMillis() + "");
        params.put("sign", MD5Util.sign(params, SybConstant.APPKEY));
        byte[] bys = http.postParams(params, true);
        String result = new String(bys, "UTF-8");
        System.out.println("ret:" + result);
        Map<String, String> map = handleResult(result);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    @RequestMapping("/query")
    public static void query(String orderid, String trxid) throws Exception {
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstant.HOSTNAME + "/query");
        http.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("cusid", SybConstant.CUSID);
        params.put("appid", SybConstant.APPID);
        params.put("trxid", trxid);
        params.put("orderid", orderid);
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("sign", MD5Util.sign(params, SybConstant.APPKEY));
        byte[] bys = http.postParams(params, true);
        String result = new String(bys, "UTF-8");
        System.out.println("ret:" + result);
        Map<String, String> map = handleResult(result);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    private static Map<String, String> handleResult(String result) throws Exception {
        Map map = MD5Util.json2Obj(result, Map.class);
        if (map == null) {
            throw new Exception("返回数据错误");
        }
        if ("SUCCESS".equals(map.get("retcode"))) {
            TreeMap tmap = new TreeMap();
            tmap.putAll(map);
            String sign = tmap.remove("sign").toString();
            String sign1 = MD5Util.sign(tmap, SybConstant.APPKEY);
            if (sign1.equals(sign)) {
                return map;
            } else {
                throw new Exception("验证签名失败");
            }

        } else {
            throw new Exception(map.get("retmsg").toString());
        }
    }
}
