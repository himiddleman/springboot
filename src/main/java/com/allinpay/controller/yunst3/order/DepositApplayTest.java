package com.allinpay.controller.yunst3.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import com.allinpay.yunst.sdk.util.RSAUtil;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class DepositApplayTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "depositApply");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 15);
        Date date = calendar.getTime();
        String ordErexpireDatetime = sdf.format(date);

        try {

            // 支付方式
            // 快捷
            HashMap<String, Object> quickPay = new HashMap<>();
            quickPay.put("bankCardNo", RSAUtil.encrypt("6228480402637874214"));
            quickPay.put("amount", 1);

            // 实名付（单笔）
            HashMap<String, Object> realnamePay = new HashMap<>();
            realnamePay.put("bankCardNo", RSAUtil.encrypt("6228487777777777777"));
            realnamePay.put("amount", 1);    // 分

            // 网关
            // String frontUrl =
            // "http://122.227.225.142:23661/gateway/getPayFront.jsp";
            String frontUrl = "http://10.55.3.236:6003/gateway/getPayFront.jsp";
            HashMap<String, Object> gatewayPay = new HashMap<>();
            gatewayPay.put("bankCode", "vbank"); // 虚拟银行，专门用于测试环境
            // gatewayPay.put("bankCode", "cmb"); //生产环境
            gatewayPay.put("payType", 1L);
            gatewayPay.put("bankCardNo", RSAUtil.encrypt("6228480402637874214")); // 借记卡
            gatewayPay.put("amount", 1);

            // 银联代扣
            HashMap<String, Object> daikouPay = new HashMap<>();
            daikouPay.put("bankCardNo", RSAUtil.encrypt("6228480318051081871"));
            daikouPay.put("amount", 100);

            // 通联通代扣
            HashMap<String, Object> tltDaikouPay = new HashMap<>();
            tltDaikouPay.put("bankCardNo", RSAUtil.encrypt("6228480318051081871"));
            tltDaikouPay.put("amount", 10000);

            //实名付（批量）
            HashMap<String, Object> realnamePay_batch = new HashMap<>();
            realnamePay_batch.put("bankCardNo", RSAUtil.encrypt("6228487777777777777"));
            realnamePay_batch.put("amount", 37);

            HashMap<String, Object> WITHHOLD_SD = new HashMap<>();
            tltDaikouPay.put("bankCardNo", RSAUtil.encrypt("6228480318051081871"));
            tltDaikouPay.put("amount", 10000);

            // 支付方式
            //收银宝刷卡支付（被扫）——支持微信、支付宝、银联、手机QQ
            HashMap<String, Object> CODEPAY_VSP = new HashMap<>();
            CODEPAY_VSP.put("authcode", "134675708230335070");
            CODEPAY_VSP.put("amount", 1);
            CODEPAY_VSP.put("payType", "");

            // 支付方式
            //收银宝刷卡支付集团（被扫）——支持微信、支付宝、银联、手机QQ
            HashMap<String, Object> CODEPAY_VSP_ORG = new HashMap<>();
            CODEPAY_VSP_ORG.put("authcode", "134558540555661761");//支付宝付款码285352993287416079，微信付款码
            CODEPAY_VSP_ORG.put("amount", 1);
            CODEPAY_VSP_ORG.put("limitPay", "no_credit");
            CODEPAY_VSP_ORG.put("vspCusid", "55058404816VQLX");

            //通联通协议支付（批量）
            HashMap<String, Object> QUICKPAY_TLT = new HashMap<>();
            QUICKPAY_TLT.put("bankCardNo", RSAUtil.encrypt("4391880821317777"));
            QUICKPAY_TLT.put("amount", 1);
            QUICKPAY_TLT.put("validate", RSAUtil.encrypt("0321"));
            QUICKPAY_TLT.put("cvv2", RSAUtil.encrypt("985"));

            // 支付方式
            //支付宝扫码支付(正扫) _集团
            HashMap<String, Object> SCAN_ALIPAY_ORG = new HashMap<>();
            SCAN_ALIPAY_ORG.put("vspCusid", "55058404816VQLX");
            SCAN_ALIPAY_ORG.put("amount", 2);
            SCAN_ALIPAY_ORG.put("payType", "");
            SCAN_ALIPAY_ORG.put("authcode", "134709641569360368");

            //微信扫码支付(正扫)
            HashMap<String, Object> SCAN_WEIXIN = new HashMap<>();
            SCAN_WEIXIN.put("amount", 5);
            SCAN_WEIXIN.put("limitPay", "no_credit");

            //支付宝扫码支付
            HashMap<String, Object> SCAN_ALIPAY = new HashMap<>();
            SCAN_ALIPAY.put("amount", 1);
            SCAN_ALIPAY.put("limitPay", "no_credit");

            //银联扫码支付
            HashMap<String, Object> SCAN_UNIONPAY = new HashMap<>();
            SCAN_UNIONPAY.put("amount", 1);
            SCAN_UNIONPAY.put("limitPay", "no_credit");

            // 支付方式
            //收银宝刷卡支付（被扫）——支持微信、支付宝、银联、手机QQ
            HashMap<String, Object> QUICKPAY_VSP = new HashMap<>();
            QUICKPAY_VSP.put("bankCardNo", RSAUtil.encrypt("4391880821317777"));
            QUICKPAY_VSP.put("amount", 5L);

            HashMap<String, Object> GATEWAY = new HashMap<>();
//            GATEWAY.put("gateid", "vbank");
            GATEWAY.put("paytype", "B2C");
            GATEWAY.put("limitPay", "no_credit");
            GATEWAY.put("amount", 5);


            //收银宝刷卡支付ORDER_VSPPAY
            HashMap<String, Object> ORDER_VSPPAY = new HashMap<>();
            ORDER_VSPPAY.put("amount", 1);

            //h5
            HashMap<String, Object> h5Pay = new HashMap<>();
            h5Pay.put("amount", 5);
            h5Pay.put("limitPay", "");

            // 组装支付方式
            HashMap<String, Object> payMethod = new HashMap<>();
//            payMethod.put("H5_CASHIER_VSP", h5Pay);
            // payMethod.put("QUICKPAY", quickPay);
//			payMethod.put("REALNAMEPAY", realnamePay);
            payMethod.put("GATEWAY_VSP", GATEWAY);
//				payMethod.put("QUICKPAY_TLT", QUICKPAY_TLT);
//			payMethod.put("QUICKPAY_VSP", QUICKPAY_VSP);
//			payMethod.put("SCAN_WEIXIN", SCAN_WEIXIN);
            // payMethod.put("WITHHOLD_UP", daikouPay);
//			 payMethod.put("WITHHOLD_SD", WITHHOLD_SD);
//			// payMethod.put("WITHHOLD_TLT", tltDaikouPay);
//			payMethod.put("REALNAMEPAY_BATCH", realnamePay_batch);
//			payMethod.put("SCAN_ALIPAY_ORG", SCAN_ALIPAY_ORG);
//			payMethod.put("CODEPAY_VSP", CODEPAY_VSP);
//			payMethod.put("CODEPAY_VSP_ORG", CODEPAY_VSP_ORG);
//            payMethod.put("ORDER_VSPPAY", ORDER_VSPPAY);
//			payMethod.put("SCAN_UNIONPAY", SCAN_UNIONPAY);


            request.put("bizUserId", "qiye01");
            request.put("bizOrderNo", System.currentTimeMillis() + "whycz");
            request.put("accountSetNo", "200126");
            request.put("amount", 5L);
            request.put("fee", 0L);
            request.put("validateType", 1L);
            request.put("frontUrl", "http://10.55.3.236:6003/gateway/getPayFront.jsp");
            request.put("backUrl", "http://116.228.64.58/yst/zfb/yibu");
            request.put("ordErexpireDatetime", ordErexpireDatetime);
            request.put("payMethod", JSONObject.toJSON(payMethod));
            request.put("goodsName", "computer");
            request.put("industryCode", "1010");
            request.put("industryName", "保险代理");
            request.put("source", 2L);
            request.put("summary", "deposit");
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
