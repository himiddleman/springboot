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
public class PayFrontAndPwdTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "pay");

        try {
            request.put("bizUserId", "2018060023");
            request.put("bizOrderNo", "1538984873880cz");
            request.put("tradeNo", "");
            request.put("verificationCode", "11111");    //测试环境 充值 实名付 验证码 11111
            request.put("consumerIp", "192.168.11.11");


            String res = YunClient.encodeOnce(request);
            res = UrlConsts.FRONTPWDTRANS + res;

            System.out.println("res: " + res);


        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
