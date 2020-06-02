package com.allinpay.controller.yunst3.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

public class ApplicationTransferTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "applicationTransfer");

        try {
            request.put("bizTransferNo", System.currentTimeMillis() + "cz");
            request.put("sourceAccountSetNo", "100002");
            request.put("targetBizUserId", "zhuyqqiye");
            request.put("targetAccountSetNo", "200001");
            request.put("amount", 100L);
            request.put("remark", "平台转账");

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
