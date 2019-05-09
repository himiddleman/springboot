package com.allinpay.controller.yunst.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.controller.yunst.util.UrlConsts;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

import java.util.HashMap;

/**
 * 4.2.7 批量托管代付（标准版）
 *
 * @author Administrator
 */
public class BatchAgentPayTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "batchAgentPay");

        try {

            // 批量托管代付列表
            JSONArray batchPayList = new JSONArray();
            HashMap<String, Object> pay1 = new HashMap<>();
            pay1.put("bizOrderNo", System.currentTimeMillis() + "cz");

            JSONArray collectPayList = new JSONArray();
            HashMap<String, Object> collect1 = new HashMap<>();
            collect1.put("bizOrderNo", System.currentTimeMillis() + "cz");
            collect1.put("amount", 10L);
            collectPayList.add(new JSONObject(collect1));

            pay1.put("collectPayList", collectPayList);
            pay1.put("bizUserId", "2018060016");
            pay1.put("accountSetNo", "200001");
            pay1.put("backUrl", UrlConsts.BACKURL);
            pay1.put("amount", 10L);
            pay1.put("fee", 1L);
            batchPayList.add(new JSONObject(pay1));

            request.put("bizBatchNo", System.currentTimeMillis() + "cz");
            request.put("batchPayList", batchPayList);
            request.put("goodsType", 1L);
            request.put("goodsNo", "A0001");
            request.put("tradeCode", "0");

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
