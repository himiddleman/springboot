package com.allinpay.controller.yunst.member;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

/**
 * 4.1.3 绑定手机
 *
 * @author Administrator
 */
public class BindPhoneTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("MemberService", "bindPhone");

        try {
            request.put("bizUserId", "tg0505");
            request.put("phone", "15197704032");
            request.put("verificationCode", "776111");

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
