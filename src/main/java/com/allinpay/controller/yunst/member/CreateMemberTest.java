package com.allinpay.controller.yunst.member;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

public class CreateMemberTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("MemberService", "createMember");
        //userId：d6b83e94-959b-4088-8f66-3fc0a85882d4
        request.put("bizUserId", "tg0505");//zzh001  xiaoc    zzh007  xiaob tg007
        request.put("memberType", 3);//个人会员
        request.put("source", 2);//pc端

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
