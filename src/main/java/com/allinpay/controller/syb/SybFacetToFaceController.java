package com.allinpay.controller.syb;

import com.allinpay.core.constant.syb.SybConstant;
import com.allinpay.core.util.syb.HttpConnectionUtil;
import com.allinpay.core.util.syb.MD5Util;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.TreeMap;

/**
 * 收银宝当面付
 */
@RequestMapping("/syb/cuspay")
@Controller
public class SybFacetToFaceController {
    public static void main(String[] args) throws Exception {
        HttpConnectionUtil connection = new HttpConnectionUtil(SybConstant.CUSPAY_URL);
        connection.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("appid", SybConstant.CUSPAY_APPID);
        //通联分配的二维码编码
        params.put("c", "NPY0QB7p");
        params.put("oid", "SHLH11235");
        params.put("amt", "1");
//        params.put("returl", "www.baidu.com");
        params.put("trxreserve", "05|Q1#张三|Q2#186-2828-9999|Q3#广州市天河区");
        params.put("sign", MD5Util.sign(params, SybConstant.CUSPAY_APPKEY));
        byte[] bytes = connection.postParams(params, true);
        String result = new String(bytes, "utf-8");
        System.out.println(result);
    }

    @RequestMapping("/code")
    public void getQRCode(HttpServletResponse response) throws Exception {
        HttpConnectionUtil connection = new HttpConnectionUtil(SybConstant.CUSPAY_URL);
        connection.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("appid", SybConstant.CUSPAY_APPID);
        //通联分配的二维码编码
        params.put("c", "NPY0QB7p");
//        params.put("oid", "");
        params.put("amt", "1");
//        params.put("returl", "www.baidu.com");
//        params.put("trxreserve", "");
        params.put("sign", MD5Util.sign(params, SybConstant.CUSPAY_APPKEY));
        byte[] bytes = connection.postParams(params, true);
        String result = new String(bytes, "utf-8");
        System.out.println(result);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print(result);
    }

    /**
     * TODO html页面能获取到request域中的值吗
     * 方法1：返回jsp,数据存到域对象中，在jsp页面取数据
     * 方法2：使用模板引擎，将数据填充好了再返回页面
     * 收钱吧
     *
     * @param id
     * @param ref
     * @param response
     * @throws Exception
     */
    @RequestMapping("/{id}")
    public String shouqianba(@PathVariable("id") String id,
                             @RequestParam("_ref") String ref,
                             HttpServletResponse response,
                             HttpServletRequest request,
                             ModelMap map) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        //这串字符的意思是什么？
        String str = "0135065099-e5634360-6234-11e9-abff-ff28d77f9ef4";
        String merchantId = "18122000327327259512";
        //18122000327327259512 商户号或者是能够映射到商户号的编码
        if (merchantId.equals(id) &&
                str.equals(ref)) {
//            response.getWriter().print("hello guys!");
            return "syb";
        }
        //方式一
        request.setAttribute("msg", merchantId);
        //方式二
//        map.put("msg", merchantId);
//        response.getWriter().print("what a pity!");
        return "err";
    }
}
