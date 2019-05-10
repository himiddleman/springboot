package com.allinpay.controller.yunst.member;

import com.allinpay.controller.yunst.util.UrlConsts;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

/**
 * 4.1.4 会员电子协议签约
 *
 * @author Administrator
 */
public class SignContractTest {

    @Test
    public void testMethod() {

        String webParamUrl = UrlConsts.SIGNCONTRACT;

        final YunRequest request = new YunRequest("MemberService", "signContract");

        try {
            request.put("bizUserId", "tg0505");
            request.put("memberType", "3");
            request.put("jumpUrl", "https://www.baidu.com");
            request.put("backUrl", UrlConsts.TESTFRONT);
            request.put("source", "2");

            String res = YunClient.encodeOnce(request);
            webParamUrl += res;
            System.out.println("webParamUrl: " + webParamUrl);

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
