package com.allinpay.controller.syb;

import com.allinpay.core.constant.syb.SybConstant;
import com.allinpay.core.util.syb.HttpConnectionUtil;
import com.allinpay.core.util.syb.MD5Util;

import java.util.TreeMap;

/**
 * 无纸化签约
 * author: tanguang
 * data: 2020/7/7
 **/
public class SignContractController {
    public static void main(String[] args) throws Exception {
        //签约地址：https://test.allinpaygd.com/expand/w0ey0X
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstant.SYB_MCHAPI_URL + "/queryelecturl");
        http.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("cusid", SybConstant.SYB_MCHAPI_CUSID);
        params.put("appid", SybConstant.SYB_MCHAPI_APPID);
        params.put("version", "11");
        params.put("mchid", "55258105399685T");
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("sign", MD5Util.sign(params, SybConstant.SYB_MCHAPI_APPKEY));
        byte[] bys = http.postParams(params, true);
        String result = new String(bys, "UTF-8");
        System.out.println(result);
    }
}
