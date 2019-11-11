package com.allinpay.controller.yunst2.member;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import com.allinpay.yunst.sdk.util.RSAUtil;
import org.junit.Test;

public class SetRealNameTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("MemberService", "setRealName");

        try {
            request.put("bizUserId", "qiye01");
            request.put("isAuth", "true");
            request.put("name", "谭光");
            request.put("identityType", "1");
            request.put("identityNo", RSAUtil.encrypt("430224199301107219"));

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
