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
    @RequestMapping("/pay")
    public String pay() throws Exception {
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstant.PREPAY_URL + "/pay");
        http.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("cusid", SybConstant.PREPAY_CUSID);
        params.put("appid", SybConstant.PREPAY_APPID);
        params.put("version", "11");
        params.put("trxamt", "1");
        params.put("reqsn", "shlh110339");
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

    private void doResult(String result) throws Exception {
        TreeMap treeMap = MD5Util.json2Obj(result, TreeMap.class);
        System.out.println("验签结果：" + MD5Util.validSign(treeMap, SybConstant.PREPAY_APPKEY));
    }
}
