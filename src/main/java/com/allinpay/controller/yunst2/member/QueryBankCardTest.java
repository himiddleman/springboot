package com.allinpay.controller.yunst2.member;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

public class QueryBankCardTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("MemberService", "queryBankCard");

        try {
            request.put("bizUserId", "WHYQY2019001");
//			request.put("cardNo", RSAUtil.encrypt("6228487777777777771"));
////			request.put("source", 2);

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
