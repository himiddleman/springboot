package com.allinpay.controller.yunst.member;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

/**
 * 4.1.5 修改绑定手机（短信验证码确认）
 *
 * @author Administrator
 */

public class ChangeBindPhoneTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("MemberService", "changeBindPhone");

        try {
            request.put("bizUserId", "tg007");
            request.put("oldPhone", "18197704032");
            request.put("newPhone", "15197704032");
            request.put("newVerificationCode", "873442");

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
