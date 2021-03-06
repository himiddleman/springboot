package com.allinpay.controller.yunst2.member;

import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

public class SignContractTest {

    @Test
    public void testMethod() {

        String webParamUrl = "http://116.228.64.55:6900/yungateway/member/signContract.html?";
//        String webParamUrl = "https://fintech.allinpay.com/yungateway/member/signContract.html?";
        final YunRequest request = new YunRequest("MemberService", "signContract");

        try {
            request.put("bizUserId", "qiye01");
            request.put("jumpUrl", "http://www.baidu.com");
            request.put("backUrl", "http://192.168.14.165:8080/yundemo/servletUI/notice");
            request.put("source", "1");

            String res = YunClient.encodeOnce(request);
            webParamUrl += res;
            System.out.println("webParamUrl: " + webParamUrl);

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
