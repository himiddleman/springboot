package com.allinpay.controller.yunst.member;

import com.allinpay.controller.yunst.util.UrlConsts;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

/**
 * 4.1.21 修改绑定手机【密码验证版】
 *
 * @author Administrator
 */
public class UpdatePhoneByPayPwdTest {

    @Test
    public void testMethod() {


        // 上海商户接入环境 外网
        String webParamUrl = UrlConsts.UPDATEPHONEBYPAYPWD;

        final YunRequest request = new YunRequest("MemberPwdService", "updatePhoneByPayPwd");

        try {
            //支付密码：allinpay111
            request.put("bizUserId", "tg0505");
            request.put("oldPhone", "15197704032");
            request.put("name", "谭光");
            request.put("identityType", 1L);
            request.put("identityNo", YunClient.encrypt("430224199301107219"));
            request.put("jumpUrl", "http://www.baidu.com");
            request.put("backUrl", UrlConsts.TESTFRONT);

            // 2.0
			/*request.put("bizUserId", "2018089008");
			request.put("name", "梅西");
			request.put("identityType", 1L);
			request.put("identityNo", YunClient.encrypt("6228480402637874221"));
			request.put("oldPhone", "13800138002");			
			request.put("jumpUrl", UrlConsts.TESTFRONT);
			request.put("backUrl", UrlConsts.TESTFRONT);*/

            String res = YunClient.encodeTwice(request);
            webParamUrl += res;
            System.out.println("webParamUrl: " + webParamUrl);

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
