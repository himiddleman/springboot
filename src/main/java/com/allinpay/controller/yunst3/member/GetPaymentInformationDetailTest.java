package com.allinpay.controller.yunst3.member;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

public class GetPaymentInformationDetailTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "getPaymentInformationDetail");

        try {
//			request.put("bizUserId", "zhuyqgr");
            request.put("bizOrderNo", "1544336802967cz");
//			request.put("accountSetNo", "dhy20181023001");
//			request.put("dateStart", "2018-06-19");
//			request.put("dateEnd", "2019-06-19");
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
