package com.allinpay.controller.yunst2.member;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

public class VspTermidServiceTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("MemberService", "vspTermidService");

        try {
            request.put("bizUserId", "WHYQY2019001");
            request.put("operationType", "query");//set-绑定，query-查询
            request.put("vspMerchantid", "330451050136261");    // 收银宝POS商户号330451050136261/00010485/11104668   收银宝当面付 328290052611277  appid:00140597 key:123456 终端CELSD8Y4
            request.put("appid", "00010485");    // 收银宝分配的APPID
            request.put("vspTermid", "11104668");

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
