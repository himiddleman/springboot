package com.allinpay.controller.yunst.member;

import com.allinpay.controller.yunst.util.UrlConsts;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

/**
 * 4.1.18 设置支付密码【密码验证版】
 *
 * @author Administrator
 */
public class SetPayPwdTest {

    @Test
    public void testMethod() {


        // 上海测试接入环境
        String webParamUrl = UrlConsts.SETPAYPWD;

        final YunRequest request = new YunRequest("MemberPwdService", "setPayPwd");

        try {
            request.put("bizUserId", "zzh001");
            request.put("phone", "18717715118");
            request.put("name", "朱大大");
            request.put("identityType", 1L);
            request.put("identityNo", YunClient.encrypt("420302199200000000"));
            request.put("jumpUrl", UrlConsts.TESTFRONT);
            request.put("backUrl", UrlConsts.TESTFRONT);

            String res = YunClient.encodeTwice(request);
            webParamUrl += res;
            System.out.println("webParamUrl: " + webParamUrl);

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
