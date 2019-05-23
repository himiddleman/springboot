package com.allinpay.controller.yunst2.member;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

public class ApplyBindBankCardTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("MemberService", "applyBindBankCard");

        try {
//			request.put("bizUserId", "zhuyqgr");
//			request.put("phone", "13611853095");	// 这个手机号 13616515002 是绑卡银行预留手机，实名支付用
//			request.put("cardCheck", 1L);	// 1L 三要素，2L 四要素
//			// 四要素
////			request.put("cardNo", RSAUtil.encrypt("6225880218855741"));
////			request.put("name", "王小二");
////			request.put("identityNo", RSAUtil.encrypt("130124198908210051"));
//			request.put("cardNo", YunClient.encrypt("6228480600000000011"));
//			request.put("name", "陈萍");
//			request.put("identityNo", YunClient.encrypt("350922198811082657"));


            request.put("bizUserId", "tg0505");
            request.put("phone", "15197704032");    // 这个手机号 13616515002 是绑卡银行预留手机，实名支付用
            request.put("cardCheck", 7L);    // 1L 三要素，2L 四要素,5L通联通账户验证
//			request.put("cardNo", YunClient.encrypt("4391880821317777"));//622848   //收银宝快捷卡号
            request.put("cardNo", YunClient.encrypt("6228480128428444870"));//通联通账户验证 绑信用卡6228491234567892019
//			request.put("cardNo", YunClient.encrypt("6225760009629085"));//ITS 四要素+短信  绑信用卡6225760009629085  借记卡6214850212618752
            request.put("name", "谭光");
            request.put("identityNo", YunClient.encrypt("430224199301107219"));
            request.put("identityType", 1L);
//			request.put("validate", YunClient.encrypt("1119"));
//			request.put("cvv2", YunClient.encrypt("102"));
//			request.put("validate", 3L);
//			request.put("cvv2", 1L);
//			request.put("isSafeCard", false);
//			request.put("unionBank", "a11111111111");
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
