package com.allinpay.controller.yunst.member;

import com.allinpay.controller.yunst.util.UrlConsts;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

/**
 * 4.1.19 修改支付密码【密码验证版�?
 *
 * @author Administrator
 */
public class UpdatePayPwdTest {

    @Test
    public void testMethod() {
        String webParamUrl = UrlConsts.UPDATEPAYPWD;

        final YunRequest request = new YunRequest("MemberPwdService", "updatePayPwd");

        try {
            request.put("bizUserId", "tg0505");
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
