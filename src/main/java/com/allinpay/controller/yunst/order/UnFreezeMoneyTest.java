package com.allinpay.controller.yunst.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

/**
 * 4.2.16 解冻金额
 *
 * @author Administrator
 */
public class UnFreezeMoneyTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "unfreezeMoney");

        try {
            request.put("bizUserId", "2018060016");
            request.put("bizFreezenNo", "1530512868585cz");
            request.put("accountSetNo", "200001");
            request.put("amount", 1L);

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
