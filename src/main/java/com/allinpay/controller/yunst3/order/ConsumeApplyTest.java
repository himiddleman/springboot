package com.allinpay.controller.yunst3.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import com.allinpay.yunst.sdk.util.RSAUtil;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class ConsumeApplyTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "consumeApply");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            // 支付方式
            // 实名付（单笔）
            HashMap<String, Object> realnamePay = new HashMap<>();
            realnamePay.put("bankCardNo", RSAUtil.encrypt("6228487777777777771"));
            realnamePay.put("amount", 1L);

            //实名付（批量）
            HashMap<String, Object> realnamePay_batch = new HashMap<>();
            realnamePay_batch.put("bankCardNo", RSAUtil.encrypt("6228487777777777771"));
            realnamePay_batch.put("amount", 37);

            //通联通协议支付（批量）
            HashMap<String, Object> QUICKPAY_TLT = new HashMap<>();
            QUICKPAY_TLT.put("bankCardNo", RSAUtil.encrypt("6228497777777771720"));
            QUICKPAY_TLT.put("amount", 1);
            QUICKPAY_TLT.put("validate", 112);
            QUICKPAY_TLT.put("validate", RSAUtil.encrypt("0322"));
            QUICKPAY_TLT.put("cvv2", RSAUtil.encrypt("322"));

            // 支付方式
            //微信扫码支付(正扫) _集团
            HashMap<String, Object> SCAN_WEIXIN_ORG = new HashMap<>();
//			SCAN_WEIXIN_ORG.put("vspCusid", "55058404816VQLX");
//			SCAN_WEIXIN_ORG.put("amount",1);
            SCAN_WEIXIN_ORG.put("limitPay", "");


            // 支付方式
            //微信扫码支付(正扫) _集团
            HashMap<String, Object> SCAN_WEIXIN = new HashMap<>();
            SCAN_WEIXIN.put("amount", 1);
            SCAN_WEIXIN.put("limitPay", "no_credit");

            //支付宝扫码支付
            HashMap<String, Object> SCAN_ALIPAY = new HashMap<>();
            SCAN_ALIPAY.put("amount", 1);
            SCAN_ALIPAY.put("limitPay", "no_credit");


            // 支付方式
            // 代金券（单笔）支付
            HashMap<String, Object> COUPON = new HashMap<>();
            COUPON.put("accountSetNo", "2000000");
            COUPON.put("amount", 1);

            //余额支付
            String accountSetNo = "200126";                //账户集编号
            JSONArray balancePay = new JSONArray();
            JSONObject balance = new JSONObject();
            balance.put("accountSetNo", accountSetNo);
            balance.put("amount", 1L);
            balancePay.add(balance);

            // 支付方式
            //收银宝刷卡支付（被扫）——支持微信、支付宝、银联、手机QQ
            HashMap<String, Object> CODEPAY_VSP = new HashMap<>();
            CODEPAY_VSP.put("authcode", "134589089934528858");
//			CODEPAY_VSP.put("amount", 1);
            CODEPAY_VSP.put("payType", "");


            // 支付方式
            //收银宝刷卡支付（被扫）——支持微信、支付宝、银联、手机QQ
            HashMap<String, Object> CODEPAY_VSP_ORG = new HashMap<>();
            CODEPAY_VSP_ORG.put("authcode", "1");
            CODEPAY_VSP_ORG.put("amount", 1);
            CODEPAY_VSP_ORG.put("payType", "1232");
            CODEPAY_VSP_ORG.put("vspCusid", "55058404816VQLX");

            // 支付方式
            //收银宝刷卡支付（被扫）——支持微信、支付宝、银联、手机QQ
            HashMap<String, Object> QUICKPAY_VSP = new HashMap<>();
            QUICKPAY_VSP.put("bankCardNo", RSAUtil.encrypt("6227003010200537446"));
            QUICKPAY_VSP.put("amount", 1L);

            //收银宝刷卡支付ORDER_VSPPAY
            HashMap<String, Object> ORDER_VSPPAY = new HashMap<>();
            ORDER_VSPPAY.put("amount", 1);

            //收银宝微信APP支付 WeChatPAYAPP_VSP
            HashMap<String, Object> WeChatPAYAPP_VSP = new HashMap<>();
            WeChatPAYAPP_VSP.put("limitPay", "");//
            WeChatPAYAPP_VSP.put("amount", 1);
            WeChatPAYAPP_VSP.put("apptype", "IOS");
            WeChatPAYAPP_VSP.put("appname", "aaaa");
            WeChatPAYAPP_VSP.put("apppackage", "com.tencent.mobileqq");
            WeChatPAYAPP_VSP.put("cusip", "10.0.0.0");

            //原生微信APP支付 WECHATPAY_APP_OPEN
            HashMap<String, Object> WECHATPAY_APP_OPEN = new HashMap<>();
            WECHATPAY_APP_OPEN.put("limitPay", "");//no_credit
            WECHATPAY_APP_OPEN.put("amount", 1);
            WECHATPAY_APP_OPEN.put("subAppId", "wx9782703e806fba55");

            //网关
            HashMap<String, Object> GATEWAY = new HashMap<>();
            GATEWAY.put("paytype", "B2C");
            GATEWAY.put("limitPay", "no_credit");
            GATEWAY.put("amount", 1);

            //余额支付



            // 组装支付方式
            HashMap<String, Object> payMethod = new HashMap<>();
//            payMethod.put("GATEWAY_VSP", GATEWAY);
//			payMethod.put("CODEPAY_VSP", CODEPAY_VSP);
            payMethod.put("QUICKPAY_VSP", QUICKPAY_VSP);
//			payMethod.put("QUICKPAY_TLT", QUICKPAY_TLT);
//			payMethod.put("CODEPAY_VSP", CODEPAY_VSP);
//			payMethod.put("CODEPAY_VSP_ORG", CODEPAY_VSP_ORG);
//			payMethod.put("SCAN_WEIXIN_ORG", SCAN_WEIXIN_ORG);
//            payMethod.put("SCAN_WEIXIN", SCAN_WEIXIN);
//			payMethod.put("WECHATPAY_APP_OPEN", WECHATPAY_APP_OPEN);
//			payMethod.put("BALANCE", balancePay);
//			payMethod.put("COUPON", COUPON);
//			payMethod.put("REALNAMEPAY_BATCH", realnamePay_batch);
//			payMethod.put("REALNAMEPAY", realnamePay);
//            payMethod.put("ORDER_VSPPAY", ORDER_VSPPAY);
//			payMethod.put("SCAN_ALIPAY", SCAN_ALIPAY);
//			payMethod.put("WeChatPAYAPP_VSP", WeChatPAYAPP_VSP);
//			payMethod.put("WECHATPAY_APP_OPEN", WECHATPAY_APP_OPEN);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR, 15);
            Date date = calendar.getTime();
            String ordErexpireDatetime = sdf.format(date);

            request.put("payerId", "qiye02");
            request.put("recieverId", "ceshi01");
            request.put("bizOrderNo", System.currentTimeMillis() + "whyxf");
            request.put("amount", 1L);
            request.put("fee", 0L);
            request.put("validateType", 1L);

            // *** split rule
            JSONArray splitRule = new JSONArray();

            HashMap<String, Object> splitRule1 = new HashMap<>();
            splitRule1.put("bizUserId", "grsunzl20190126002");
            splitRule1.put("accountSetNo", "200087");
            splitRule1.put("amount", 2L);
            splitRule1.put("fee", 0L);
            splitRule1.put("remark", " 消费一级分账");

//			JSONArray splitRule2List1 = new JSONArray();
//			HashMap<String, Object> splitRule2List = new HashMap<>();
//			splitRule2List.put("bizUserId", "2018060022");
//			splitRule2List.put("accountSetNo", "200001");
//			splitRule2List.put("amount", 20L);
//			splitRule2List.put("fee", 0L);
//			splitRule2List.put("remark", "消费二级分账");
//			
//			splitRule2List1.add(new JSONObject(splitRule2List));			
//			
//			splitRule2List.clear();
//			splitRule2List.put("bizUserId", "2018060023");
//			splitRule2List.put("accountSetNo", "200001");
//			splitRule2List.put("amount", 10L);
//			splitRule2List.put("fee", 0L);
//			splitRule2List.put("remark", "消费二级分账");
//			splitRule2List1.add(new JSONObject(splitRule2List));			
//			
//			splitRule1.put("splitRuleList", splitRule2List1);
            splitRule.add(new JSONObject(splitRule1));

//			request.put("splitRule", splitRule);

            request.put("frontUrl", "http://192.168.14.165:8080/yundemo/servletUI/jumpback");
            request.put("backUrl", "http://116.228.64.58/yst/zfb/yibu");
            request.put("ordErexpireDatetime", ordErexpireDatetime);
            request.put("payMethod", payMethod);
            request.put("goodsName", "computer");
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
