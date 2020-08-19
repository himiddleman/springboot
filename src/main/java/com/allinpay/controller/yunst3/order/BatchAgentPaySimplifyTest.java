package com.allinpay.controller.yunst3.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

import java.util.HashMap;

public class BatchAgentPaySimplifyTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "batchAgentPaySimplifyCheck");

        try {

            // 批量托管代付列表
            JSONArray batchPayList = new JSONArray();
            HashMap<String, Object> pay1 = new HashMap<>();
            pay1.put("bizOrderNo", System.currentTimeMillis() + "SDF");

            pay1.put("collectbizOrderNo", "1595223529023SDS");
            pay1.put("bizUserId", "qiye01");
            pay1.put("accountSetNo", "200126");
            pay1.put("backUrl", "http://192.168.14.165:8080/yundemo/servletUI/notice");
            pay1.put("amount", 1L);
            pay1.put("fee", 0L);
            pay1.put("extendInfo", "222222222222222");
            batchPayList.add(new JSONObject(pay1));

            request.put("bizBatchNo", System.currentTimeMillis() + "bp");
            request.put("batchPayList", batchPayList);
//            request.put("goodsType", 1L);
//            request.put("goodsNo", "A0001");
            request.put("tradeCode", "2003");

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
