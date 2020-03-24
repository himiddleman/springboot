package com.allinpay.controller.syb;

import com.allinpay.core.constant.syb.SybConstant;
import com.allinpay.core.util.syb.HttpConnectionUtil;
import com.allinpay.core.util.syb.MD5Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping("/syb/gateway")
public class SybGatewayController {
    public static void main(String[] args) throws Exception {
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstant.HOSTNAME + "/pay");
        http.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("cusid", SybConstant.CUSID);
        params.put("appid", SybConstant.APPID);
        params.put("charset", "UTF-8");
        params.put("returl", SybConstant.RET_URL);
//        params.put("notifyurl", SybConstant.NOTFIY_URL);
        params.put("trxamt", "1");
//        params.put("gateid", "0105");
        params.put("orderid", "shlh12345");
        params.put("gateid", "0103");
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("paytype", "B2C");
        params.put("sign", MD5Util.sign(params, SybConstant.APPKEY));
        byte[] bys = http.postParams(params, true);
        String result = new String(bys, "UTF-8");
        System.out.println("ret:" + result);
//        String content = "<html><head><body><form action=\"http://localhost:8080/jump/redirect\" name=\"postform\" method=\"post\"><input type=\"hidden\" name=\"reqMsg\" value=\"2E81303434383030303030303030303031393939393030303120202000003030303020202020202000303030303030323030B22040800061800A000000001000004130303030303030303030303030303030313030343137313433393534373332373938343831363330393930343430313438313636303030DAE4C4B0BFC6BCBCD3D0CFDEB9ABCBBE20202020202020202020202020202020202020202020202031303141534744303136DAE4C4B0BFC6BCBCD3D0CFDEB9ABCBBE4F443031383131313939343132303030303733323739384F4C3030323139534130323431323139303736373931313031313125CDA8C1AAD6A7B8B6544C303134323031393034313731343439353431353630353530302020202020202020202020202020202020202020202020202020202020204355504E4D7C7C7C3132322E3232342E39302E3232367C30373968747470733A2F2F7673702E616C6C696E7061792E636F6D2F6170697765622F676174657761792F63616C6C6261636B2F6974733F6F7264657249643D313131393934313230303030373332373938303830313035303030303031383131313939343132303030303733323739380000000000000000\"/></form></body><script type=\"text/javascript\">document.forms['postform'].submit();</script></html>";
//        response.setContentType("text/html;charset=utf-8");
//        response.getWriter().print(result);
    }

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
    public static void refund() throws Exception {
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstant.HOSTNAME + "/refund");
        http.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("cusid", SybConstant.CUSID);
        params.put("appid", SybConstant.APPID);
        params.put("trxamt", "1");
        params.put("trxid", "111994120000732892");
        params.put("orderid", "shlh011038");
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
    public static void query() throws Exception {
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstant.HOSTNAME + "/query");
        http.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("cusid", SybConstant.CUSID);
        params.put("appid", SybConstant.APPID);
        params.put("orderid", "shlh011038");
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

    @RequestMapping("/pay")
    public static void pay(HttpServletResponse response) throws Exception {
        HttpConnectionUtil http = new HttpConnectionUtil(SybConstant.HOSTNAME + "/pay");
        http.init();
        TreeMap<String, String> params = new TreeMap<>();
        params.put("cusid", SybConstant.CUSID);
        params.put("appid", SybConstant.APPID);
        params.put("charset", "UTF-8");
        params.put("returl", SybConstant.RET_URL);
//        params.put("notifyurl", SybConstant.NOTFIY_URL);
        params.put("trxamt", "1");
//        params.put("gateid", "0105");
        params.put("orderid", "shlh011038");
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("paytype", "B2C");
        params.put("sign", MD5Util.sign(params, SybConstant.APPKEY));
        //方式一：直接完成重定向
//        return "redirect:"+SybConstant.HOSTNAME + "/pay?"+assembleParams(params);

        //方式二：使用response 往前端写html
        byte[] bytes = http.postParams(params, true);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print(new String(bytes, "UTF-8"));
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

    public static String assembleParams(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder outBuf = new StringBuilder();
        boolean isNotFirst = false;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (isNotFirst)
                outBuf.append('&');
            isNotFirst = true;
            outBuf
                    .append(entry.getKey())
                    .append('=')
                    .append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        System.out.println("参数:" + outBuf.toString());
        return outBuf.toString();
    }
}
