package com.allinpay.controller.yunst.member;

import com.allinpay.controller.yunst.util.UrlConsts;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

/**
 * 4.1.20 重置支付密码【密码验证版】
 *
 * @author Administrator
 */
public class ResetPayPwdTest {

    @Test
    public void testMethod() {


        String webParamUrl = UrlConsts.RESETPAYPWD;

        final YunRequest request = new YunRequest("MemberPwdService", "resetPayPwd");

        try {
            request.put("bizUserId", "tg0505");
            request.put("phone", "15197704032");
            request.put("name", "谭光");
            request.put("identityType", 1L);
            request.put("identityNo", YunClient.encrypt("430224199301107219"));
            request.put("jumpUrl", "http://www.baidu.com");
            request.put("backUrl", UrlConsts.TESTFRONT);

            String res = YunClient.encodeTwice(request);
            webParamUrl += res;
            System.out.println("webParamUrl: " + webParamUrl);

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
