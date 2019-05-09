package com.allinpay.controller.yunst.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

/**
 * 4.2.17 退款申请
 *
 * @author Administrator
 */
public class RefundTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "refund");

        try {
            request.put("bizOrderNo", System.currentTimeMillis() + "tk");
            request.put("oriBizOrderNo", "1531119172281cz");
            request.put("bizUserId", "2018070001");
            request.put("refundType", "");

            request.put("refundList", "");
            request.put("backUrl", "");
            request.put("amount", 100L);
            request.put("couponAmount", "");
            request.put("feeAmount", "");
            request.put("extendInfo", "");


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
