package com.allinpay.controller.yunst.member;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

/**
 * 4.1.24 会员绑定支付账户用户标识
 *
 * @author Administrator
 */
public class ApplyBindAcctTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("MemberService", "applyBindAcct");

        try {
            request.put("bizUserId", "zzh001");
            request.put("operationType", "set");    // set 绑定 query 查询
            request.put("acctType", "");
            request.put("acct", "");

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
