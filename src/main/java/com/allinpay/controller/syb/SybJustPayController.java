package com.allinpay.controller.syb;

import com.allinpay.core.constant.syb.SybConstant;
import com.allinpay.core.util.syb.HttpConnectionUtil;
import com.allinpay.core.util.syb.MD5Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.TreeMap;

/**
 * 收银宝付款
 */
@RequestMapping("/syb/justpay")
@Controller
public class SybJustPayController {
    @RequestMapping("/pay")
    public void pay(HttpServletResponse response) throws Exception {
        HttpConnectionUtil connection = new HttpConnectionUtil(SybConstant.PAY_URL);
        connection.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("appid", SybConstant.PAY_APPID);
        //通联分配的二维码编码
        params.put("cusid", SybConstant.PAY_CUSID);
        params.put("reqsn", "shlh110114");
        params.put("trxamt", "1");
        params.put("acctno", "6227003010200537446");
        params.put("acctname", "谭光");
        params.put("acctprop", "0");
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("sign", MD5Util.sign(params, SybConstant.PAY_APPKEY));
        byte[] bytes = connection.postParams(params, true);
        String result = new String(bytes, "utf-8");
        System.out.println(result);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print(result);
    }
}
