package com.allinpay.controller.yunst2.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

public class PayTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "pay");

        try {
            request.put("bizUserId", "ceshi01");
            request.put("bizOrderNo", "1573548478840whyxf");
//            request.put("tradeNo", "{\"sign\":\"\",\"tphtrxcrtime\":\"\",\"tphtrxid\":0,\"trxflag\":\"trx\",\"trxsn\":\"\"}");
            String tradeNo = "{\"sign\":\"\",\"tphtrxcrtime\":\"\",\"tphtrxid\":0,\"trxflag\":\"trx\",\"trxsn\":\"\"}";  //交易编号
            request.put("verificationCode", "11111");    //测试环境 充值 实名付 验证码 11111
            request.put("consumerIp", "192.168.11.11");

            String res = YunClient.request(request);
//            String res = YunClient.encodeOnce(request);
//            res = "http://116.228.64.55:6900/yungateway/frontTrans.do?" + res;
//            res = "http://116.228.64.55:6900/yungateway/pwd/payOrder.html?" + res;
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
