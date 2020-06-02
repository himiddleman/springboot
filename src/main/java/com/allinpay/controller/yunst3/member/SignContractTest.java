package com.allinpay.controller.yunst3.member;

import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

public class SignContractTest {

    @Test
    public void testMethod() {

        //String webParamUrl = "http://122.227.225.142:23661/yungateway/member/signContract.html?";
        String webParamUrl = "https://fintech.allinpay.com/yungateway/member/signContract.html?";
        final YunRequest request = new YunRequest("MemberService", "signContract");

        try {
            request.put("bizUserId", "WHYQY2019002");
            request.put("memberType", "2");
            request.put("jumpUrl", "http://www.baidu.com");
            request.put("backUrl", "http://192.168.14.165:8080/yundemo/servletUI/notice");
            request.put("source", "2");

            String res = YunClient.encodeOnce(request);
            webParamUrl += res;
            System.out.println("webParamUrl: " + webParamUrl);

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
