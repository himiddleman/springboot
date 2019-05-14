package com.allinpay.controller.yunst.order;

import com.allinpay.controller.yunst.util.UrlConsts;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

/**
 * 4.2.8 确认支付（后台+短信验证码确认）
 * 4.2.9 确认支付（前台+短信验证码确认）
 * 4.2.10 确认支付【前台+密码验证版】
 *
 * @author Administrator
 */
public class PayTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "pay");

        try {
            request.put("bizUserId", "tg0505");
            request.put("bizOrderNo", "1557803930581cz");
//            request.put("tradeNo", "1126719700428918784");
//            request.put("verificationCode", "111111");    //测试环境 充值 实名付 验证码 111111
//            request.put("jumpUrl", "http://www.bai.com");
            request.put("consumerIp", "192.168.11.11");

//            String res = YunClient.request(request);
            String res = YunClient.encodeOnce(request);
            res = UrlConsts.FRONTTRANS + res;
//            res = UrlConsts.FRONTPWDTRANS + res;

            System.out.println("res: " + res);
			
			/*JSONObject resp = JSON.parseObject(res);
			System.out.println("status=[" + resp.getString("status") + "]");
			System.out.println("signedValue=[" + resp.getString("signedValue") + "]");
			System.out.println("sign=[" + resp.getString("sign") + "]");
			System.out.println("errorCode=[" + resp.getString("errorCode") + "]");
			System.out.println("message=[" + resp.getString("message") + "]");*/
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
