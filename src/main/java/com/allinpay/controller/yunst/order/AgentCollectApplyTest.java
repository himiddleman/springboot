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
 * 4.2.5 托管代收申请（标准版）
 *
 * @author Administrator
 */
public class AgentCollectApplyTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "agentCollectApply");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            // 支付方式
            // 实名付（单笔）
            HashMap<String, Object> realnamePay = new HashMap<>();
            realnamePay.put("bankCode", "vbank");
            realnamePay.put("payerCardNo", YunClient.encrypt("6228480402637874221"));
            realnamePay.put("amount", 32L);
            realnamePay.put("payType", 1);

            // 组装支付方式
            HashMap<String, Object> payMethod = new HashMap<>();
            payMethod.put("GATEWAY", realnamePay);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR, 15);
            Date date = calendar.getTime();
            String ordErexpireDatetime = sdf.format(date);

            // 收款列表
            JSONArray receiverList = new JSONArray();
            HashMap<String, Object> receiver1 = new HashMap<>();
            receiver1.put("bizUserId", "2018060020");
            receiver1.put("amount", 10L);
            receiverList.add(new JSONObject(receiver1));

            HashMap<String, Object> receiver2 = new HashMap<>();
            receiver2.put("bizUserId", "2018060021");
            receiver2.put("amount", 10L);
            receiverList.add(new JSONObject(receiver2));

            HashMap<String, Object> receiver3 = new HashMap<>();
            receiver3.put("bizUserId", "2018060022");
            receiver3.put("amount", 10L);
            receiverList.add(new JSONObject(receiver3));

            request.put("bizOrderNo", System.currentTimeMillis() + "cz");
            request.put("payerId", "tg0505"); // bizUserId 2018060016
            request.put("recieverList", receiverList);
//            request.put("goodsType", 0L);
//            request.put("goodsNo", "A0001");
            request.put("tradeCode", "1003");
            request.put("amount", 32L);
            request.put("fee", 2L);
            request.put("validateType", 1L); // 1 短信验证
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
