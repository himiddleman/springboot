package com.allinpay.controller.yunst.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.controller.yunst.util.UrlConsts;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * 4.2.4 消费申请
 *
 * @author Administrator
 */
public class ConsumeApplyTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "consumeApply");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            // 支付方式
            // 网关支付
            HashMap<String, Object> realnamePay = new HashMap<>();
            realnamePay.put("bankCode", "vbank");
            realnamePay.put("payerCardNo", YunClient.encrypt("6228480402637874221"));
            realnamePay.put("amount", 200L);
            realnamePay.put("payType", 1);

            // 组装支付方式
            HashMap<String, Object> payMethod = new HashMap<>();
            payMethod.put("GATEWAY", realnamePay);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR, 15);
            Date date = calendar.getTime();
            String ordErexpireDatetime = sdf.format(date);

            request.put("payerId", "2018070001");
            request.put("recieverId", "2018060015");
            request.put("bizOrderNo", System.currentTimeMillis() + "cz");
            request.put("amount", 200L);
            request.put("fee", 2L);
            request.put("validateType", 1L);

            // *** split rule
            JSONArray splitRule = new JSONArray();

            HashMap<String, Object> splitRule1 = new HashMap<>();
            splitRule1.put("bizUserId", "2018060016");
            splitRule1.put("accountSetNo", "200001");
            splitRule1.put("amount", 50L);
            splitRule1.put("fee", 0L);
            splitRule1.put("remark", " 消费一级分账");

            JSONArray splitRule2List1 = new JSONArray();
            HashMap<String, Object> splitRule2List = new HashMap<>();
            splitRule2List.put("bizUserId", "2018060022");
            splitRule2List.put("accountSetNo", "200001");
            splitRule2List.put("amount", 20L);
            splitRule2List.put("fee", 0L);
            splitRule2List.put("remark", "消费二级分账");

            splitRule2List1.add(new JSONObject(splitRule2List));

            splitRule2List.clear();
            splitRule2List.put("bizUserId", "2018060023");
            splitRule2List.put("accountSetNo", "200001");
            splitRule2List.put("amount", 10L);
            splitRule2List.put("fee", 0L);
            splitRule2List.put("remark", "消费二级分账");
            splitRule2List1.add(new JSONObject(splitRule2List));

            splitRule1.put("splitRuleList", splitRule2List1);
            splitRule.add(new JSONObject(splitRule1));

            request.put("splitRule", splitRule);

            request.put("frontUrl", UrlConsts.RECIEVEPAYBACK);
            request.put("backUrl", UrlConsts.RECIEVEPAYBACK);
            request.put("ordErexpireDatetime", ordErexpireDatetime);
            request.put("payMethod", payMethod);
            request.put("goodsName", "computer");
            request.put("goodsDesc", "computer made in china");
            request.put("industryCode", "1010");
            request.put("industryName", "保险代理");
            request.put("source", 2L);
            request.put("summary", "consume");
            request.put("extendInfo", "this is extendInfo");

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
