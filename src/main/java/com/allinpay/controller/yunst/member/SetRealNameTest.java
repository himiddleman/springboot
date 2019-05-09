package com.allinpay.controller.yunst.member;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

/**
 * 4.1.6 个人实名认证
 *
 * @author Administrator
 */
public class SetRealNameTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("MemberService", "setRealName");

        try {
			/*request.put("bizUserId", "2018089011");
			request.put("isAuth", "true");
			request.put("name", "梅西");
			request.put("identityType", "1");
			request.put("identityNo", YunClient.encrypt("6228480402637874221"));*/

            request.put("bizUserId", "zzh001");
            request.put("isAuth", "true");
            request.put("name", "朱大大");
            request.put("identityType", "1");
            request.put("identityNo", YunClient.encrypt("420302199200000000"));
			
			/*request.put("bizUserId", "2018089007");
			request.put("isAuth", "true");
			request.put("name", "王小二");
			request.put("identityType", "1");
			request.put("identityNo", YunClient.encrypt("610422198600000000"));*/

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
