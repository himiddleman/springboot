package com.allinpay.controller.yunst3.member;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import com.allinpay.yunst.sdk.util.RSAUtil;
import org.junit.Test;

public class BankCardChangeBindPhoneTest {

    @Test
    public void testMethod() throws Exception {

        final YunRequest request = new YunRequest("MemberService", "bankCardChangeBindPhone");
        request.put("bizUserId", "acct");
        request.put("cardNo", RSAUtil.encrypt("6227003010200537446"));
        request.put("phone", "15197704032");
        request.put("name", "谭光");
        request.put("cardCheck", 7);
        request.put("identityType", 1);
        request.put("identityNo", RSAUtil.encrypt("430224199301107219"));

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
