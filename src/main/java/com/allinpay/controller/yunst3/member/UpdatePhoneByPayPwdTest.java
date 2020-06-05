package com.allinpay.controller.yunst3.member;

import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import com.allinpay.yunst.sdk.util.RSAUtil;
import org.junit.Test;

public class UpdatePhoneByPayPwdTest {

    @Test
    public void testMethod() {

        // 宁波商户接入环境
        // webParamUrl = "http://122.227.225.142:23661/pwd/updatePhoneByPayPwd.html?";
        // 2.0生产环境环境
        String webParamUrl = "https://fintech.allinpay.com/yungateway/pwd/updatePhoneByPayPwd.html?";

        final YunRequest request = new YunRequest("MemberPwdService", "updatePhoneByPayPwd");

        try {
            request.put("bizUserId", "acct");
            request.put("name", "谭光");
            request.put("identityType", 1L);
            request.put("identityNo", RSAUtil.encrypt("430224199301107219"));
            request.put("oldPhone", "88888888888");
            request.put("jumpUrl", "http://122.227.225.142:23663/testFront.jsp");
            request.put("backUrl", "http://122.227.225.142:23663/testFront.jsp");

            String res = YunClient.encodeOnce(request);
            webParamUrl += res;
            System.out.println("webParamUrl: " + webParamUrl);

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
