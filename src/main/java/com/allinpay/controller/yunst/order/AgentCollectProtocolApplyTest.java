package com.allinpay.controller.yunst.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.HashMap;

public class AgentCollectProtocolApplyTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "agentCollectProtocolApply");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {

            request.put("bizOrderNo", "");
            request.put("payerId", "");
            request.put("protocolNo", "");


            JSONArray recieverList = new JSONArray();
            JSONObject recieverListObj = new JSONObject();
            recieverListObj.put("bizUserId", "");
            recieverListObj.put("amount", "");
            recieverList.add(recieverListObj);
            request.put("recieverList", recieverList);//目前仅支持一个

            // 支付方式  仅支持余额
            JSONArray balancePay = new JSONArray();
            JSONObject balancePayObj = new JSONObject();
            balancePayObj.put("accountSetNo", "100000");
            balancePayObj.put("amount", 1);
            balancePay.add(balancePayObj);

            // 组装支付方式
            HashMap<String, Object> payMethod = new HashMap<>();
            payMethod.put("BALANCE", balancePay);

            request.put("payMethod", payMethod);
            request.put("goodsType", "");
            request.put("goodsNo", "");
            request.put("tradeCode", "");
            request.put("amount", "");
            request.put("fee", "");
            request.put("backUrl", "");
            request.put("ordErexpireDatetime", "");//yyyy-MM-dd HH:mm:ss 订单最长时效为24小时，默认为最长时效
            request.put("goodsName", "");
            request.put("goodsDesc", "");
            request.put("industryCode", "");
            request.put("industryName", "");
            request.put("source", "");
            request.put("summary", "");
            request.put("extendInfo", "");


            JSONArray splitRule = new JSONArray();
            JSONArray splitRule3 = new JSONArray();

            //一级分账
            JSONObject splistRule1 = new JSONObject();
            splistRule1.put("bizUserId", "testA");
            splistRule1.put("amount", 6L);
            splistRule1.put("fee", 1L);
            splistRule1.put("remark", "aaaaa");

            splitRule.add(splistRule1);
            System.out.println("===一级分账===   " + splitRule);

            JSONArray splitRule1List = new JSONArray();
            //二级分账
            JSONObject splistRule2 = new JSONObject();
            splistRule2.put("bizUserId", "testB");
            splistRule2.put("amount", 6L);
            splistRule2.put("fee", 1L);
            splistRule2.put("remark", "aaaaa");


            splitRule1List.add(splistRule2);
            splistRule1.put("splitRuleList", splitRule1List);

            splitRule3.add(splistRule1);
            System.out.println("===二级分账===   " + splitRule);


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
