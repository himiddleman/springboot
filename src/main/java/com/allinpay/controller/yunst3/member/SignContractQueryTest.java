package com.allinpay.controller.yunst3.member;

import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

public class SignContractQueryTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("MemberService", "signContractQuery");
        request.put("bizUserId", "qiye01");
        request.put("jumpUrl", "www.baidu.com");
        request.put("source", 2);

        try {
            System.out.println(" http://116.228.64.55:6900/yungateway/member/signContractQuery.html?" + YunClient.encodeOnce(request));
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
