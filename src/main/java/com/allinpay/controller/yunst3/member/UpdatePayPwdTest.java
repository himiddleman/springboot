package com.allinpay.controller.yunst3.member;

import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import com.allinpay.yunst.sdk.util.RSAUtil;
import org.junit.Test;

public class UpdatePayPwdTest {

    @Test
    public void testMethod() {

        // 宁波商户接入环境
        //String webParamUrl = "http://122.227.225.142:23661/pwd/updatePayPwd.html?";

        // 上海商户接入环境
        String webParamUrl = "http://192.168.14.70:8080/pwd/updatePayPwd.html?";

        final YunRequest request = new YunRequest("MemberPwdService", "updatePayPwd");

        try {
            request.put("bizUserId", "ceshi01");
            request.put("name", "谭光");
            request.put("identityType", 1L);
            request.put("identityNo", RSAUtil.encrypt("430224199301107219"));
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
