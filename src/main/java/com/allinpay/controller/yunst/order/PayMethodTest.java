package com.allinpay.controller.yunst.order;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import org.junit.Test;

import java.util.HashMap;


public class PayMethodTest {


    @Test
    public static void main(String[] args) {   //加密

        HashMap<String, Object> realnamePay = new HashMap<>();


        String cardNoPlain = "6228480402637874215";
        String cardNoEnc = "";
        try {
            System.out.println("===加密前卡号明文===   " + cardNoPlain);
            cardNoEnc = YunClient.encrypt(cardNoPlain);
            System.out.println("===加密后卡号密文===   " + cardNoEnc);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        System.out.println("=======================支付方式=========================================   ");
        //网关 JSONObject
        JSONObject gatewayPay = new JSONObject();
        gatewayPay.put("bankCode", "vbank");  //虚拟银行，专门用于测试环境
        gatewayPay.put("payType", 1L);
        gatewayPay.put("amount", 1);

        System.out.println("===网关(JSONObject)===   " + gatewayPay);

        //订单POS-陕西
        JSONObject POSPAY = new JSONObject();
        POSPAY.put("amount", 1l);
        System.out.println("===订单POS-陕西===   " + POSPAY);

        //订单POS-深圳
        JSONObject POSPAY_SZ = new JSONObject();
        POSPAY.put("amount", 1l);
        System.out.println("===订单POS-陕西===   " + POSPAY_SZ);

        //通联通协议支付
        JSONObject QUICKPAY_TLT = new JSONObject();
        String bankcard = "";
        try {
            bankcard = YunClient.encrypt("420302199200000000");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        QUICKPAY_TLT.put("bankCardNo", bankcard);
        QUICKPAY_TLT.put("amount", 1l);
        System.out.println("===通联通协议支付===   " + QUICKPAY_TLT);


        //实名付（单笔）
        JSONObject REALNAMEPAY = new JSONObject();
        try {
            bankcard = YunClient.encrypt("420302199200000000");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        REALNAMEPAY.put("bankCardNo", bankcard);
        REALNAMEPAY.put("amount", 1l);
        System.out.println("===实名付（单笔）===   " + REALNAMEPAY);

        //实名付（批量）
        JSONObject REALNAMEPAY_BATCH = new JSONObject();
        try {
            bankcard = YunClient.encrypt("420302199200000000");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        REALNAMEPAY_BATCH.put("bankCardNo", bankcard);
        REALNAMEPAY_BATCH.put("amount", 1l);
        System.out.println("===实名付（批量）===   " + REALNAMEPAY_BATCH);

        //收银宝快捷支付
        JSONObject QUICKPAY_VSP = new JSONObject();
        try {
            bankcard = YunClient.encrypt("420302199200000000");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        REALNAMEPAY_BATCH.put("bankCardNo", bankcard);
        REALNAMEPAY_BATCH.put("amount", 1l);
        System.out.println("===实名付（批量）===   " + QUICKPAY_VSP);


        //收银宝快捷支付
        JSONObject WECHATPAY_MINIPROGRAM = new JSONObject();

        WECHATPAY_MINIPROGRAM.put("limitPay", "");
        WECHATPAY_MINIPROGRAM.put("acct", "");
        WECHATPAY_MINIPROGRAM.put("amount", 1l);
        System.out.println("===微信小程序支付（借、贷）===   " + WECHATPAY_MINIPROGRAM);

        //微信小程序支付（借、贷）_集团
        JSONObject WECHATPAY_MINIPROGRAM_ORG = new JSONObject();

        WECHATPAY_MINIPROGRAM.put("vspCusid", "");
        WECHATPAY_MINIPROGRAM.put("limitPay", "");
        WECHATPAY_MINIPROGRAM.put("amount", 1l);
        WECHATPAY_MINIPROGRAM.put("acct", "");
        System.out.println("===微信小程序支付（借、贷）_集团===   " + WECHATPAY_MINIPROGRAM_ORG);
				
				/*//微信APP支付     （收银宝）
				JSONObject WeChatPAYAPP_VSP = new JSONObject(); 
			
				WeChatPAYAPP_VSP.put("limitPay","");
				WeChatPAYAPP_VSP.put("amount",""); 
				WeChatPAYAPP_VSP.put("apptype",""); 
				WeChatPAYAPP_VSP.put("appname", 1l); 
				WeChatPAYAPP_VSP.put("apppackage", ""); 
				WeChatPAYAPP_VSP.put("cusip", ""); 
				System.out.println("===微信APP支付     （收银宝）===   "+WeChatPAYAPP_VSP);*/
				
				
				/*//微信APP支付_集团（收银宝
				JSONObject WeChatPAYAPP_VSP_ORG = new JSONObject(); 
			
				WeChatPAYAPP_VSP_ORG.put("vspCusid ","");
				WeChatPAYAPP_VSP_ORG.put("limitPay",""); 
				WeChatPAYAPP_VSP_ORG.put("amount",""); 
				WeChatPAYAPP_VSP_ORG.put("apptype", 1l); 
				WeChatPAYAPP_VSP_ORG.put("appname", ""); 
				WeChatPAYAPP_VSP_ORG.put("apppackage", ""); 
				WeChatPAYAPP_VSP_ORG.put("cusip", ""); 
				System.out.println("===微信APP支付_集团（收银宝）===   "+WeChatPAYAPP_VSP_ORG);*/


        //微信原生APP支付
        JSONObject WECHATPAY_APP_OPEN = new JSONObject();

        WECHATPAY_APP_OPEN.put("subAppId ", "");
        WECHATPAY_APP_OPEN.put("amount", "");
        WECHATPAY_APP_OPEN.put("limitPay", "");

        System.out.println("===微信原生APP支付===   " + WECHATPAY_APP_OPEN);


        //微信扫码支付(正扫)
        JSONObject SCAN_WEIXIN = new JSONObject();

        SCAN_WEIXIN.put("payType ", "");
        SCAN_WEIXIN.put("amount", "");
        System.out.println("===微信扫码支付(正扫)===   " + SCAN_WEIXIN);

        //微信扫码支付(正扫) _集团
        JSONObject SCAN_WEIXIN_ORG = new JSONObject();

        SCAN_WEIXIN_ORG.put("vspCusid ", "");
        SCAN_WEIXIN_ORG.put("payType", "");
        SCAN_WEIXIN_ORG.put("amount", "");
        System.out.println("===微信扫码支付(正扫) _集团===   " + SCAN_WEIXIN_ORG);


        //支付宝扫码支付(正扫)
        JSONObject SCAN_ALIPAY = new JSONObject();

        SCAN_ALIPAY.put("payType ", "");
        SCAN_ALIPAY.put("amount", "");
        System.out.println("===支付宝扫码支付(正扫)===   " + SCAN_ALIPAY);

        //支付宝扫码支付(正扫) _集团
        JSONObject SCAN_ALIPAY_ORG = new JSONObject();
        SCAN_ALIPAY_ORG.put("vspCusid", "");
        SCAN_ALIPAY_ORG.put("payType ", "");
        SCAN_ALIPAY_ORG.put("amount", "");
        System.out.println("===支付宝扫码支付(正扫) _集团)===   " + SCAN_ALIPAY_ORG);

        //微信JS支付（公众号）
        JSONObject WECHAT_PUBLIC = new JSONObject();
        WECHAT_PUBLIC.put("payType", "");
        WECHAT_PUBLIC.put("acct ", "");
        WECHAT_PUBLIC.put("amount", "");
        System.out.println("===微信JS支付（公众号）===   " + WECHAT_PUBLIC);

        //微信JS支付（公众号）_集团
        JSONObject WECHAT_PUBLIC_ORG = new JSONObject();
        WECHAT_PUBLIC_ORG.put("vspCusid ", "");
        WECHAT_PUBLIC_ORG.put("amount ", "");
        WECHAT_PUBLIC_ORG.put("acct", "");
        WECHAT_PUBLIC_ORG.put("payType", "");
        System.out.println("===微信JS支付（公众号）_集团===   " + WECHAT_PUBLIC_ORG);


        //支付宝JS支付（生活号）
        JSONObject ALIPAY_SERVICE = new JSONObject();
        ALIPAY_SERVICE.put("acct ", "");
        ALIPAY_SERVICE.put("amount ", "");

        System.out.println("===支付宝JS支付（生活号）===   " + ALIPAY_SERVICE);


        //支付宝JS支付_集团
        JSONObject ALIPAY_SERVICE_ORG = new JSONObject();
        ALIPAY_SERVICE_ORG.put("vspCusid ", "");
        ALIPAY_SERVICE_ORG.put("amount ", "");
        ALIPAY_SERVICE_ORG.put("acct ", "");
        System.out.println("===支付宝JS支付_集团===   " + ALIPAY_SERVICE_ORG);

        //QQ钱包JS支付
        JSONObject QQ_WALLET = new JSONObject();
        QQ_WALLET.put("amount ", "");
        System.out.println("===QQ钱包JS支付===   " + QQ_WALLET);

        //QQ钱包JS支付_集团
        JSONObject QQ_WALLET_ORG = new JSONObject();
        QQ_WALLET_ORG.put("vspCusid ", "");
        QQ_WALLET_ORG.put("amount ", "");
        System.out.println("===QQ钱包JS支付_集团===   " + QQ_WALLET_ORG);


        //微信刷卡支付（被扫）
        JSONObject CODEPAY_W = new JSONObject();
        CODEPAY_W.put("payType ", "");
        CODEPAY_W.put("amount ", "");
        CODEPAY_W.put("authcode ", "");
        System.out.println("===微信刷卡支付（被扫）===   " + CODEPAY_W);


        //支付宝刷卡支付(被扫)
        JSONObject CODEPAY_A = new JSONObject();
        CODEPAY_A.put("amount ", "");
        CODEPAY_A.put("authcode ", "");
        System.out.println("===微信刷卡支付（被扫）===   " + CODEPAY_A);


        //QQ钱包刷卡支付(被扫)
        JSONObject CODEPAY_Q = new JSONObject();
        CODEPAY_Q.put("amount ", "");
        CODEPAY_Q.put("authcode ", "");
        System.out.println("===QQ钱包刷卡支付(被扫)===   " + CODEPAY_Q);

        //账户余额 JSONArray(里面为Object)
        JSONArray balancePay = new JSONArray();
        JSONObject balancePayObj = new JSONObject();
        balancePayObj.put("accountSetNo", "100000");
        balancePayObj.put("amount", 1);
        balancePay.add(balancePayObj);
        System.out.println("===账户余额 (JSONArray)===   " + gatewayPay);


        //微信H5支付
        JSONObject WECHATPAY_H5 = new JSONObject();
        WECHATPAY_H5.put("limitPay", "");
        WECHATPAY_H5.put("amount", "");
        WECHATPAY_H5.put("apptype", "");
        WECHATPAY_H5.put("appname", "");
        WECHATPAY_H5.put("apppackage", "");
        WECHATPAY_H5.put("cusip", "");

        System.out.println("===微信H5支付===   " + WECHATPAY_H5);


        //代金券（不建议使用，建议使用下面的批量代金券方式）
        JSONObject COUPON = new JSONObject();
        COUPON.put("limitPay", "");
        COUPON.put("amount", "");

        System.out.println("===代金券（不建议使用，建议使用下面的批量代金券方式）===   " + COUPON);


        //批量代金券
        JSONArray COUPONLISTArray = new JSONArray();
        JSONObject COUPONLIST = new JSONObject();

        COUPONLIST.put("amount", "");
        COUPONLIST.put("accountSetNo", "");
        COUPONLISTArray.add(COUPONLIST);

        System.out.println("===批量代金券===   " + COUPONLISTArray);

        //通联通代付
        JSONObject WITHDRAW_TLT = new JSONObject();
        WITHDRAW_TLT.put("payTypeName", "withdraw_tlt");
        WITHDRAW_TLT.put("unionBank", "");
        WITHDRAW_TLT.put("bankName", "");
        WITHDRAW_TLT.put("province", "");
        WITHDRAW_TLT.put("city", "");

        System.out.println("===通联通代付===   " + WITHDRAW_TLT);

        //新移动H5快捷支付
        JSONObject QUICKPAY_H5 = new JSONObject();
        QUICKPAY_H5.put("amount", "");
        try {
            bankcard = YunClient.encrypt("420302199200000000");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        QUICKPAY_H5.put("bankCardNo", bankcard);
        System.out.println("===新移动H5快捷支付===   " + QUICKPAY_H5);


        //新移动PC快捷支付
        JSONObject QUICKPAY_PC = new JSONObject();
        QUICKPAY_PC.put("amount", "");
        try {
            bankcard = YunClient.encrypt("420302199200000000");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        QUICKPAY_PC.put("bankCardNo", bankcard);
        System.out.println("===新移动PC快捷支付===   " + QUICKPAY_PC);


        //收银宝POS当面付及订单模式支付
        JSONObject ORDER_VSPPAY = new JSONObject();
        ORDER_VSPPAY.put("amount", "");

        System.out.println("===收银宝POS当面付及订单模式支付===   " + ORDER_VSPPAY);

        //微信APP支付_集团（收银宝）
        JSONObject WECHATPAY_APP_ORG = new JSONObject();
        WECHATPAY_APP_ORG.put("vspCusid ", "");
        WECHATPAY_APP_ORG.put("limitPay", "");
        WECHATPAY_APP_ORG.put("amount", "");

        System.out.println("===微信APP支付_集团（收银宝）===   " + WECHATPAY_APP_ORG);


        //支付宝原生APP支付
        JSONObject ALIPAY_APP_OPEN = new JSONObject();
        ALIPAY_APP_OPEN.put("amount ", "");
        ALIPAY_APP_OPEN.put("enablePayChannels", "");
        ALIPAY_APP_OPEN.put("paysummary", "");

        System.out.println("===支付宝原生APP支付===   " + ALIPAY_APP_OPEN);


        //通用虚拟出金
        JSONObject VIRTUAL_OUT = new JSONObject();
        VIRTUAL_OUT.put("amount ", "");
        VIRTUAL_OUT.put("extChannelNo", "");
        VIRTUAL_OUT.put("extAccount", "");
        VIRTUAL_OUT.put("paysummary", "");

        System.out.println("===通用虚拟出金===   " + VIRTUAL_OUT);


        //通用虚拟入金
        JSONObject VIRTUAL_IN = new JSONObject();
        VIRTUAL_IN.put("amount ", "");
        VIRTUAL_IN.put("extChannelNo", "");
        VIRTUAL_IN.put("extAccount", "");
        VIRTUAL_IN.put("paysummary", "");

        System.out.println("===通用虚拟入金===   " + VIRTUAL_IN);


        //山东代收
        JSONObject WITHHOLD_SD = new JSONObject();
        WITHHOLD_SD.put("bankCardNo ", "");
        WITHHOLD_SD.put("province", "");
        WITHHOLD_SD.put("city", "");
        WITHHOLD_SD.put("amount", "");
        WITHHOLD_SD.put("paysummary", "");

        System.out.println("===山东代收===   " + WITHHOLD_SD);


        //山东代付
        JSONObject WITHDRAW_SD = new JSONObject();
        WITHDRAW_SD.put("amount ", "");
        WITHDRAW_SD.put("unionBank", "");
        WITHDRAW_SD.put("bankName", "");
        WITHDRAW_SD.put("province", "");
        WITHDRAW_SD.put("city", "");
        WITHDRAW_SD.put("paysummary", "");

        System.out.println("===山东代付===   " + WITHDRAW_SD);


        //微信APP支付 （收银宝）
        JSONObject WeChatPAYAPP_VSP = new JSONObject();
        WeChatPAYAPP_VSP.put("limitPay", "no_credit");  //非贷记卡：no_credit 借、贷记卡：””，需要传空字符串，不能不传
        WeChatPAYAPP_VSP.put("amount", 1L);//支付金额，单位：分 测试环境金额：9-交易成功
        WeChatPAYAPP_VSP.put("apptype", "Android");//枚举值：IOS，Android，Wap
        WeChatPAYAPP_VSP.put("appname", "王者荣耀");  //商户app名称；app名称或者网站名称例如 王者荣耀
        WeChatPAYAPP_VSP.put("apppackage", "com.tencent.mobileqq");//商户app包名；app包名或者网站地址，例如 com.tencent.mobileqq
        WeChatPAYAPP_VSP.put("cusip", "127.0.0.1");//用户下单及调起支付的终端IP  注：最大长度16
        System.out.println("===微信APP支付     （收银宝）===   " + WeChatPAYAPP_VSP);

        //微信APP支付_集团（收银宝）
        JSONObject WeChatPAYAPP_VSP_ORG = new JSONObject();
        WeChatPAYAPP_VSP_ORG.put("vspCusid", "123123123");//收银宝子商户号，与微信appid对应
        WeChatPAYAPP_VSP_ORG.put("limitPay", "no_credit");  //非贷记卡：no_credit 借、贷记卡：””，需要传空字符串，不能不传
        WeChatPAYAPP_VSP_ORG.put("amount", 1L);//支付金额，单位：分 测试环境金额：9-交易成功
        WeChatPAYAPP_VSP_ORG.put("apptype", "Android");//枚举值：IOS，Android，Wap
        WeChatPAYAPP_VSP_ORG.put("appname", "王者荣耀");  //商户app名称；app名称或者网站名称例如 王者荣耀
        WeChatPAYAPP_VSP_ORG.put("apppackage", "com.tencent.mobileqq");//商户app包名；app包名或者网站地址，例如 com.tencent.mobileqq
        WeChatPAYAPP_VSP_ORG.put("cusip", "127.0.0.1");//用户下单及调起支付的终端IP  注：最大长度16
        System.out.println("===微信APP支付     （收银宝）===   " + WeChatPAYAPP_VSP_ORG);


        System.out.println("=======================支付方式=========================================   ");


        JSONArray splitRule = new JSONArray();
        JSONArray splitRule3 = new JSONArray();

        //一级分账
        JSONObject splistRule1 = new JSONObject();
        splistRule1.put("bizUserId", "testA");
        splistRule1.put("amount", 6L);
        splistRule1.put("fee", 1L);
        splistRule1.put("remark", "aaaaa");

        splitRule.add(splistRule1);
        System.out.println("===一级分账===   " + splitRule);

        JSONArray splitRule1List = new JSONArray();
        //二级分账
        JSONObject splistRule2 = new JSONObject();
        splistRule2.put("bizUserId", "testB");
        splistRule2.put("amount", 6L);
        splistRule2.put("fee", 1L);
        splistRule2.put("remark", "aaaaa");


        splitRule1List.add(splistRule2);
        splistRule1.put("splitRuleList", splitRule1List);

        splitRule3.add(splistRule1);
        System.out.println("===二级分账===   " + splitRule);


        //组装支付方式
        JSONObject payMethod = new JSONObject();
        payMethod.put("GATEWAY", gatewayPay);
        payMethod.put("POSPAY", POSPAY);
        payMethod.put("POSPAY_SZ", POSPAY_SZ);
        payMethod.put("QUICKPAY_TLT", QUICKPAY_TLT);
        payMethod.put("REALNAMEPAY", REALNAMEPAY);
        payMethod.put("REALNAMEPAY_BATCH", REALNAMEPAY_BATCH);

        payMethod.put("QUICKPAY_VSP", QUICKPAY_VSP);
        payMethod.put("WECHATPAY_MINIPROGRAM", WECHATPAY_MINIPROGRAM);
        payMethod.put("WECHATPAY_MINIPROGRAM_ORG", WECHATPAY_MINIPROGRAM_ORG);
        payMethod.put("WeChatPAYAPP_VSP", WeChatPAYAPP_VSP);
        payMethod.put("WeChatPAYAPP_VSP_ORG", WeChatPAYAPP_VSP_ORG);
        //payMethod.put("WECHATPAY_APP", WECHATPAY_APP);  已停用
        //	payMethod.put("WECHATPAY_APP_ORG", WECHATPAY_APP_ORG);已停用
        payMethod.put("WECHATPAY_APP_OPEN", WECHATPAY_APP_OPEN);
        payMethod.put("SCAN_WEIXIN", SCAN_WEIXIN);
        payMethod.put("SCAN_WEIXIN_ORG", SCAN_WEIXIN_ORG);
        payMethod.put("SCAN_ALIPAY", SCAN_ALIPAY);
        payMethod.put("SCAN_ALIPAY_ORG", SCAN_ALIPAY_ORG);
        payMethod.put("WECHAT_PUBLIC", WECHAT_PUBLIC);
        payMethod.put("WECHAT_PUBLIC_ORG", WECHAT_PUBLIC_ORG);
        payMethod.put("ALIPAY_SERVICE", ALIPAY_SERVICE);
        payMethod.put("QQ_WALLET", QQ_WALLET);
        payMethod.put("QQ_WALLET_ORG", QQ_WALLET_ORG);
        payMethod.put("CODEPAY_W", CODEPAY_W);
        payMethod.put("CODEPAY_A", CODEPAY_A);
        payMethod.put("CODEPAY_Q", CODEPAY_Q);
        payMethod.put("BALANCE", balancePay);
        payMethod.put("WECHATPAY_H5", WECHATPAY_H5);
        payMethod.put("COUPON", COUPON);
        payMethod.put("COUPONLIST", COUPONLIST);
        payMethod.put("WITHDRAW_TLT", WITHDRAW_TLT);
        payMethod.put("QUICKPAY_H5", QUICKPAY_H5);
        payMethod.put("QUICKPAY_PC", QUICKPAY_PC);
        payMethod.put("ORDER_VSPPAY", ORDER_VSPPAY);
        payMethod.put("WECHATPAY_APP_ORG", WECHATPAY_APP_ORG);
        payMethod.put("ALIPAY_APP_OPEN", ALIPAY_APP_OPEN);
        payMethod.put("VIRTUAL_OUT", VIRTUAL_OUT);
        payMethod.put("VIRTUAL_IN", VIRTUAL_IN);
        payMethod.put("WITHHOLD_SD", WITHHOLD_SD);
        payMethod.put("WITHDRAW_SD", WITHDRAW_SD);


        payMethod.put("REALNAMEPAY", REALNAMEPAY);


        System.out.println("===组装后payMethod===   " + payMethod);


    }
}
