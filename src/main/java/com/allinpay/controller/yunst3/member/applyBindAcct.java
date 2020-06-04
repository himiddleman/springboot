package com.allinpay.controller.yunst3.member;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

public class applyBindAcct {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("MemberService", "applyBindAcct");
        request.put("bizUserId", "acct");//个人会员 ceshi01 企业会员 qiye01
        request.put("operationType", "set");//企业会员-2，个人会员-3
        request.put("acctType", "weChatPublic");
        request.put("acct", "opn0buG9JhliFQIOoNGmok9_cWsU");

        try {
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
