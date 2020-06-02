package com.allinpay.controller.yunst3.member;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

public class GetPayeeFundsInTransitDetailTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "getPayeeFundsInTransitDetail");

        try {
            request.put("bizUserId", "zhuyqgr");
//			request.put("bizOrderNo", "1233333331Sdba233b33abcd11dd12");
//			request.put("accountSetNo", "dhy20181023001");
//			request.put("dateStart", "2018-10-25");
//			request.put("dateEnd", "2019-10-25");
//			
            String res = YunClient.request(request);
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
