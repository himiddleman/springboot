package com.allinpay.controller.yunst2.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import com.allinpay.yunst.sdk.util.RSAUtil;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

public class AgentCollectApplyTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "agentCollectApply");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            // 支付方式
            // 实名付（单笔）
            HashMap<String, Object> realnamePay = new HashMap<>();
            realnamePay.put("bankCardNo", RSAUtil.encrypt("6228487777777777771"));
            realnamePay.put("amount", 1);


            //网关支付
            HashMap<String, Object> getwayPay = new HashMap<>();
            getwayPay.put("bankCode", "vbank");
            getwayPay.put("payType", 1L);
            getwayPay.put("amount", 1L);

            // 支付方式
            // 代金券（单笔）
            HashMap<String, Object> COUPON = new HashMap<>();
            COUPON.put("accountSetNo", "2000000");
            COUPON.put("amount", 1L);

            //余额支付
            String accountSetNo = "200001";                //账户集编号
            JSONArray balancePay = new JSONArray();
            JSONObject balance = new JSONObject();
            balance.put("accountSetNo", accountSetNo);
            balance.put("amount", 37L);

            // 支付方式
            //收银宝刷卡支付（被扫）——支持微信、支付宝、银联、手机QQ
            HashMap<String, Object> CODEPAY_VSP = new HashMap<>();
            CODEPAY_VSP.put("authcode", "6227838799325716926");
            CODEPAY_VSP.put("amount", 1);
            CODEPAY_VSP.put("payType", "no_credit");

            // 支付方式
            //收银宝刷卡支付集团（被扫）——支持微信、支付宝、银联、手机QQ
            HashMap<String, Object> CODEPAY_VSP_ORG = new HashMap<>();
            CODEPAY_VSP_ORG.put("authcode", "6266202232268890740");//支付宝付款码285352993287416079，微信付款码，云闪付
            CODEPAY_VSP_ORG.put("amount", 1);
            CODEPAY_VSP_ORG.put("limitPay", "");
            CODEPAY_VSP_ORG.put("vspCusid", "55058404816VQLX");

            //批量代金券
            List<Map<String, Object>> couponList = new ArrayList<>();
            HashMap<String, Object> coupon1 = new HashMap<>();
            coupon1.put("amount", 500L);
//			coupon1.put("accountSetNo", "100002");
            couponList.add(coupon1);
            HashMap<String, Object> coupon2 = new HashMap<>();
            coupon2.put("amount", 500L);
//			coupon2.put("accountSetNo", "100002");
            couponList.add(coupon2);

            //通联通协议支付（批量）
            HashMap<String, Object> QUICKPAY_TLT = new HashMap<>();
            QUICKPAY_TLT.put("bankCardNo", RSAUtil.encrypt("6228487777777771720"));
            QUICKPAY_TLT.put("amount", 1);
//			QUICKPAY_TLT.put("validate",RSAUtil.encrypt("0321"));
//			QUICKPAY_TLT.put("cvv2", RSAUtil.encrypt("322"));

            //实名付（批量）
            HashMap<String, Object> realnamePay_batch = new HashMap<>();
            realnamePay_batch.put("bankCardNo", RSAUtil.encrypt("6228487777777777771"));
            realnamePay_batch.put("amount", 36000000000L);

            // 支付方式
            //收银宝刷卡支付（被扫）——支持微信、支付宝、银联、手机QQ
            HashMap<String, Object> QUICKPAY_VSP = new HashMap<>();
            QUICKPAY_VSP.put("bankCardNo", RSAUtil.encrypt("4391880821317777"));
            QUICKPAY_VSP.put("amount", 1L);

            // 组装支付方式
            HashMap<String, Object> payMethod = new HashMap<>();
//			payMethod.put("REALNAMEPAY", realnamePay);
//			payMethod.put("QUICKPAY_TLT", QUICKPAY_TLT);
//			payMethod.put("CODEPAY_VSP", CODEPAY_VSP);
            payMethod.put("CODEPAY_VSP_ORG", CODEPAY_VSP_ORG);
//			payMethod.put("GATEWAY", getwayPay);
//			payMethod.put("BALANCE", balancePay);
//			payMethod.put("COUPONLIST", couponList);
//			payMethod.put("COUPON", COUPON);
//			payMethod.put("REALNAMEPAY_BATCH", realnamePay_batch);
//			payMethod.put("QUICKPAY_VSP", QUICKPAY_VSP);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR, 15);
            Date date = calendar.getTime();
            String ordErexpireDatetime = sdf.format(date);

            // 收款列表
            JSONArray receiverList = new JSONArray();
            HashMap<String, Object> receiver1 = new HashMap<>();
            receiver1.put("bizUserId", "WHYQY2019001");
            receiver1.put("amount", 1);
            receiverList.add(new JSONObject(receiver1));

//			HashMap<String, Object> receiver2 = new HashMap<>();
//			receiver2.put("bizUserId", "2018060021");
//			receiver2.put("amount", 10L);			
//			receiverList.add(new JSONObject(receiver2));
//			
//			HashMap<String, Object> receiver3 = new HashMap<>();
//			receiver3.put("bizUserId", "2018060022");
//			receiver3.put("amount", 10L);			
//			receiverList.add(new JSONObject(receiver3));


            request.put("bizOrderNo", System.currentTimeMillis() + "ds");
            request.put("payerId", "WHYGR2019001"); // bizUserId  付款方
            request.put("recieverList", receiverList);
            request.put("goodsType", 2L);
            request.put("bizGoodsNo", "12121133211122234");
            request.put("tradeCode", "1003");
            request.put("amount", 1L);
            request.put("fee", 0L);
            request.put("validateType", 0L); // 1 短信验证
            request.put("frontUrl", "http://192.168.14.165:8080/yundemo/servletUI/jumpback");
            request.put("backUrl", "http://172.16.190.46:8080/yuncallback/mock/notify?");
            request.put("ordErexpireDatetime", ordErexpireDatetime);
            request.put("payMethod", payMethod);
            request.put("goodsName", "海艳商超");
            request.put("goodsDesc", "computer made in china");
            request.put("industryCode", "1010");
            request.put("industryName", "保险代理");
            request.put("source", 2L);
            request.put("summary", "consume");
            request.put("extendInfo", "this is extendInfo");

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
