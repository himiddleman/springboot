package com.allinpay.controller.yunst.member;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

public class GetProtocolDetail {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("MemberService", "getProtocolDetail");

        try {
            request.put("payerId", "zzh001");
            request.put("protocolNo", "1044874671775813632");

            String res = YunClient.request(request);
            System.out.println("res: " + res);

            JSONObject resp = JSON.parseObject(res);

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
