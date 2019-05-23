package com.allinpay.controller.yunst2.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

public class RefundTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "refund");

        try {
            request.put("bizUserId", "WHYGR2019001");
            request.put("bizOrderNo", System.currentTimeMillis() + "whydsrefund");
            request.put("oriBizOrderNo", "1550043689254whyxf");
            request.put("amount", 1L);
            request.put("couponAmount", 1L);
            request.put("feeAmount", 0L);
            request.put("refundType", "D1");
            request.put("backUrl", "http://172.16.190.46:8080/yuncallback/mock/notify?");

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
