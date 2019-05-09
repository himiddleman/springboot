package com.allinpay.controller.yunst.order;

import com.allinpay.controller.yunst.util.UrlConsts;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

/**
 * 4.2.9 确认支付（前台+短信验证码确认）
 *
 * @author Administrator
 */
public class PayFrontAndTextTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "pay");

        try {
            request.put("bizUserId", "2018060023");
            request.put("bizOrderNo", "1538986699004cz");
            request.put("tradeNo", "");
            request.put("verificationCode", "11111");    //测试环境 充值 实名付 验证码 11111
            request.put("consumerIp", "192.168.11.11");


            String res = YunClient.encodeOnce(request);
            res = UrlConsts.FRONTTRANS + res;

            System.out.println("res: " + res);


        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
