package com.allinpay.controller.yunst3.member;

import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import com.allinpay.yunst.sdk.util.RSAUtil;
import org.junit.Test;

public class ResetPayPwdTest {

    @Test
    public void testMethod() {

        // 宁波商户接入环境
        //String webParamUrl = "http://122.227.225.142:23661/pwd/resetPayPwd.html?";

        // 上海商户接入环境
        String webParamUrl = "http://192.168.14.70:8080/pwd/resetPayPwd.html?";

        final YunRequest request = new YunRequest("MemberPwdService", "resetPayPwd");

        try {
            request.put("bizUserId", "2018072001");
            request.put("name", "梅西");
            request.put("phone", "13800138002");
            request.put("identityType", 1L);
            request.put("identityNo", RSAUtil.encrypt("130124198908210051"));
            request.put("jumpUrl", "http://122.227.225.142:23663/testFront.jsp");
            request.put("backUrl", "http://122.227.225.142:23663/testFront.jsp");

            String res = YunClient.encodeTwice(request);
            webParamUrl += res;
            System.out.println("webParamUrl: " + webParamUrl);

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
