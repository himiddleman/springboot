package com.allinpay.controller.yunst.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.controller.yunst.util.UrlConsts;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * 4.2.2 充值申请
 *
 * @author Administrator
 */
public class DepositApplyTest {

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
            quickPay.put("bankCardNo", YunClient.encrypt("6228480402637874214"));
            quickPay.put("amount", 1);

            // 实名付（单笔）
            HashMap<String, Object> realnamePay = new HashMap<>();
            realnamePay.put("bankCardNo", YunClient.encrypt("6228480402637874221"));
            realnamePay.put("amount", 100000);    // 分

            // 网关
            String frontUrl = UrlConsts.GETPAYFRONT;
            HashMap<String, Object> gatewayPay = new HashMap<>();
            gatewayPay.put("bankCode", "vbank"); // 虚拟银行，专门用于测试环境
            // gatewayPay.put("bankCode", "cmb"); //生产环境
            gatewayPay.put("payType", 1L);
            gatewayPay.put("bankCardNo", YunClient.encrypt("6228480402637874214")); // 借记卡
            gatewayPay.put("amount", 1);

            // 银联代扣
            HashMap<String, Object> daikouPay = new HashMap<>();
            daikouPay.put("bankCardNo", YunClient.encrypt("6228480318051081871"));
            daikouPay.put("amount", 100);

            // 通联通代扣
            HashMap<String, Object> tltDaikouPay = new HashMap<>();
            tltDaikouPay.put("bankCardNo", YunClient.encrypt("6228480318051081871"));
            tltDaikouPay.put("amount", 10000);

            // 组装支付方式
            HashMap<String, Object> payMethod = new HashMap<>();
            // payMethod.put("QUICKPAY", quickPay);
            //	payMethod.put("REALNAMEPAY", realnamePay);
            payMethod.put("GATEWAY", gatewayPay);
            // payMethod.put("WITHHOLD_UP", daikouPay);
            // payMethod.put("WITHHOLD_TLT", tltDaikouPay);

            JSONArray balancePay = new JSONArray();
            JSONObject balancePayObj = new JSONObject();
            balancePayObj.put("accountSetNo", "100000");
            balancePayObj.put("amount", 100000L);
            balancePay.add(balancePayObj);

            //payMethod.put("BALANCE", balancePay);

            request.put("bizUserId", "2018060023");
            request.put("bizOrderNo", System.currentTimeMillis() + "cz");
            request.put("accountSetNo", "200001");
            request.put("amount", 1);
            request.put("fee", 0L);
            request.put("validateType", 1L);
            request.put("frontUrl", frontUrl);
            request.put("backUrl", UrlConsts.RECIEVEPAYBACK);
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
