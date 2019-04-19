package com.allinpay.controller.syb;

import com.allinpay.core.constant.syb.SybConstant;
import com.allinpay.core.util.syb.HttpConnectionUtil;
import com.allinpay.core.util.syb.MD5Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.TreeMap;

/**
 * 收银宝银行卡验证
 */
@RequestMapping("/syb/bankcard")
@Controller
public class SybBankVerifyController {
    @RequestMapping("/twoelement")
    public void getQRCode(HttpServletResponse response) throws Exception {
        HttpConnectionUtil connection = new HttpConnectionUtil(SybConstant.BANK_URL + "bankverify2");
        connection.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("appid", SybConstant.BANK_APPID);
        //通联分配的二维码编码
        params.put("cusid", SybConstant.BANK_CUSID);
        params.put("reqsn", System.currentTimeMillis() + "");
        params.put("cardno", "");
        params.put("name", "谭光");
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("sign", MD5Util.sign(params, SybConstant.BANK_APPKEY));
        byte[] bytes = connection.postParams(params, true);
        String result = new String(bytes, "utf-8");
        System.out.println(result);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print(result);
    }

    @RequestMapping("/threeelement")
    public void threeelement(HttpServletResponse response) throws Exception {
        HttpConnectionUtil connection = new HttpConnectionUtil(SybConstant.BANK_URL + "bankverify3");
        connection.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("appid", SybConstant.BANK_APPID);
        //通联分配的二维码编码
        params.put("cusid", SybConstant.BANK_CUSID);
        params.put("reqsn", System.currentTimeMillis() + "");
        params.put("cardno", "");
        params.put("name", "谭光");
        params.put("idno", "");
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("sign", MD5Util.sign(params, SybConstant.BANK_APPKEY));
        byte[] bytes = connection.postParams(params, true);
        String result = new String(bytes, "utf-8");
        System.out.println(result);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print(result);
    }

    @RequestMapping("/fourelement")
    public void fourelement(HttpServletResponse response) throws Exception {
        HttpConnectionUtil connection = new HttpConnectionUtil(SybConstant.BANK_URL + "bankverify4");
        connection.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("appid", SybConstant.BANK_APPID);
        //通联分配的二维码编码
        params.put("cusid", SybConstant.BANK_CUSID);
        params.put("reqsn", System.currentTimeMillis() + "");
        params.put("cardno", "");
        params.put("name", "谭光");
        params.put("idno", "");
        params.put("phone", "");
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("sign", MD5Util.sign(params, SybConstant.BANK_APPKEY));
        byte[] bytes = connection.postParams(params, true);
        String result = new String(bytes, "utf-8");
        System.out.println(result);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print(result);
    }
}
