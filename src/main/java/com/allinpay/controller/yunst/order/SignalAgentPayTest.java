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
 * 4.2.6 单笔托管代付（标准版）
 *
 * @author Administrator
 */
public class SignalAgentPayTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "signalAgentPay");

        try {

            // 源托管代收订单付款信息
            JSONArray collectPayList = new JSONArray();
            HashMap<String, Object> collect1 = new HashMap<>();
            collect1.put("bizOrderNo", "");
            collect1.put("amount", 10L);
            collectPayList.add(new JSONObject(collect1));

            HashMap<String, Object> collect2 = new HashMap<>();
            collect2.put("bizOrderNo", "");
            collect2.put("amount", 10L);
            collectPayList.add(new JSONObject(collect2));

            HashMap<String, Object> collect3 = new HashMap<>();
            collect3.put("bizOrderNo", "");
            collect3.put("amount", 10L);
            collectPayList.add(new JSONObject(collect3));

            request.put("bizOrderNo", System.currentTimeMillis() + "cz");
            request.put("collectPayList", collectPayList);
            ;
            request.put("bizUserId", "2018060016");
            request.put("accountSetNo", "200001");
            request.put("backUrl", UrlConsts.RECIEVEPAYBACK);
            request.put("amount", 30L);
            request.put("fee", 2L);
            request.put("tradeCode", "1001");
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
