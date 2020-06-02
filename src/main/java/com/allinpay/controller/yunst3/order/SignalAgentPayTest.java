package com.allinpay.controller.yunst3.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

import java.util.HashMap;

public class SignalAgentPayTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "signalAgentPay");

        try {

            // 源托管代收订单付款信息
            JSONArray collectPayList = new JSONArray();
            HashMap<String, Object> collect1 = new HashMap<>();
            collect1.put("bizOrderNo", "1549007009040ds");
            collect1.put("amount", 1L);
            collectPayList.add(new JSONObject(collect1));

//			HashMap<String, Object> collect2 = new HashMap<>();
//			collect2.put("bizOrderNo", "1544420784406cz");
//			collect2.put("amount", 10L);			
//			collectPayList.add(new JSONObject(collect2));
//			
//			HashMap<String, Object> collect3 = new HashMap<>();
//			collect3.put("bizOrderNo", "");
//			collect3.put("amount", 10L);			
//			collectPayList.add(new JSONObject(collect3));			

            request.put("bizOrderNo", System.currentTimeMillis() + "df");
            request.put("collectPayList", collectPayList);
            ;
            request.put("bizUserId", "WHYQY2019001");
            request.put("accountSetNo", "200087");
            request.put("backUrl", "http://172.16.190.46:8080/yuncallback/mock/notify?");
            request.put("amount", 1L);
            request.put("fee", 0L);
            request.put("goodsType", 2L);
            request.put("bizGoodsNo", "12121133211122234");
            request.put("tradeCode", "2003");
            request.put("summary", "signalAgentPay");
            request.put("extendInfo", "this is extendInfo");
            ;

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
