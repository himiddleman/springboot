package com.allinpay.controller.yunst3.member;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

public class createBankSubAcctNoTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("MemberService", "createBankSubAcctNo");
        request.put("accountSetNo", "200126");//个人会员 ceshi01 企业会员 qiye01
        request.put("bizUserId", "qiye02");//企业会员-2，个人会员-3
        request.put("acctOrgType", 1);

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
