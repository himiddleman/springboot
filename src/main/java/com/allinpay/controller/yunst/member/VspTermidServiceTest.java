package com.allinpay.controller.yunst.member;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

/**
 * 4.1.22 绑定/查询收银宝当面付二维码编号及收银宝POS终端号
 *
 * @author Administrator
 */
public class VspTermidServiceTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("MemberService", "vspTermidService");

        try {
            request.put("bizUserId", "zzh001");
            request.put("operationType", "set");    // set 绑定 query 查询
            request.put("vspMerchantid", "8215840481600WG-21");
            request.put("appid", "00014353-20");
            request.put("vspTermid", "10294979-20");

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
