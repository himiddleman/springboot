package com.allinpay.controller.yunst.member;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

/***
 * 4.1.11 请求绑定银行卡
 * @author Administrator
 *
 */
public class ApplyBindBankCardTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("MemberService", "applyBindBankCard");

        try {
            request.put("bizUserId", "tg0505");
            request.put("phone", "15197704032");    // 这个手机号 13616515002 是绑卡银行预留手机，实名支付用
            request.put("cardCheck", 2L);    // 1L 三要素，2L 四要素


            // 三要素
            request.put("cardNo", YunClient.encrypt("6228480000000000000"));//622848
            request.put("name", "谭光");
            request.put("identityType", "1");
            request.put("identityNo", YunClient.encrypt("430224199301107219"));//D20180925107504 D2019050954629

            // 四要素
            //request.put("cardNo", YunClient.encrypt("6228480402637874221"));
            //request.put("name", "梅西");
            //request.put("identityNo", YunClient.encrypt("130124198908210051"));

            // 收银宝快捷支付签约（有银行范围）
//            request.put("cardNo", YunClient.encrypt("6228480402637874221"));
//            request.put("identityNo", YunClient.encrypt("130124198908210051"));
//            request.put("phone", "18717715117");
//            request.put("name", "梅西");
//            request.put("identityType", 1L);
//            request.put("validate", YunClient.encrypt("202012"));
//            request.put("cvv2", "");

            // 银行卡四要素验证（万鉴通，全部银行）
//            request.put("cardNo", YunClient.encrypt("6228480402637874221"));
//            request.put("identityNo", YunClient.encrypt("130124198908210051"));
//            request.put("name", "梅西");
//            request.put("identityType", 1L);
//
//            request.put("identityType", 1L);
//            request.put("validate", YunClient.encrypt("202012"));
//            request.put("cvv2", "");
//            request.put("isSafeCard", false);
//            request.put("unionBank", "");

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
