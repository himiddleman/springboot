package com.allinpay.controller.yunst3.member;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

public class GetSubAcctNoInfoForHTTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("MemberService", "getSubAcctNoInfoForHT");
        request.put("bizUserId", "qiye02");
        request.put("subAcctNo", "9120001000212583112");
        request.put("jumpUrl", "www.baidu.com");
        request.put("backUrl", "www.baidu.com");

        try {
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
