package com.allinpay.controller.yunst.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

/**
 * 4.2.8 确认支付（后台+短信验证码确认）
 *
 * @author Administrator
 */
public class PayBackAndTextTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "pay");

        try {
            request.put("bizUserId", "2018060023");
            request.put("bizOrderNo", "1538984873880cz");
            request.put("tradeNo", "");
            request.put("verificationCode", "11111");    //测试环境 充值 实名付 验证码 11111
            request.put("consumerIp", "192.168.11.11");

            String res = YunClient.request(request);
            //String res = YunClient.encodeOnce(request);
            //res= UrlConsts.FRONTTRANS+res;

            System.out.println("res: " + res);

            JSONObject resp = JSON.parseObject(res);
            System.out.println("status=[" + resp.getString("status") + "]");
            System.out.println("signedValue=[" + resp.getString("signedValue") + "]");
            System.out.println("sign=[" + resp.getString("sign") + "]");
            System.out.println("errorCode=[" + resp.getString("errorCode") + "]");
            System.out.println("message=[" + resp.getString("message") + "]");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
