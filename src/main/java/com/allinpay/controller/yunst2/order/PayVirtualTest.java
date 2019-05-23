package com.allinpay.controller.yunst2.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

public class PayVirtualTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "payVirtual");

        try {
            request.put("bizUserId", "2018060023");
            request.put("bizOrderNo", "1532420711955cz");
            request.put("flag", "confirm");
            request.put("extPayMethod", "realNamePay");
            request.put("extTradeNo", System.currentTimeMillis() + "cz");
            request.put("summary", "summary");
            request.put("consumerIp", "192.168.11.11");

            String res = YunClient.request(request);
            System.out.println("res: " + res);

            JSONObject resp = JSON.parseObject(res);
            System.out.println("status=[" + resp.getString("status") + "]");
            System.out.println("signedValue=[" + resp.getString("signedValue") + "]");
            System.out.println("sign=[" + resp.getString("sign") + "]");
            System.out.println("errorCode=[" + resp.getString("errorCode") + "]");
            System.out.println("message=[" + resp.getString("message") + "]");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
