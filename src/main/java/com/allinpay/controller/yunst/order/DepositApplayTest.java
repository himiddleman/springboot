package com.allinpay.controller.yunst.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
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
            quickPay.put("bankCardNo", YunClient.encrypt("6228480402637874214"));
            quickPay.put("amount", 1);

            // 实名付（单笔）
            HashMap<String, Object> realnamePay = new HashMap<>();
            realnamePay.put("bankCardNo", YunClient.encrypt("6228480402637874221"));
            realnamePay.put("amount", 1);    // 分

            // 网关
            // String frontUrl =
            // "http://122.227.225.142:23661/gateway/getPayFront.jsp";
            String frontUrl = "http://10.55.3.236:6003/gateway/getPayFront.jsp";
            HashMap<String, Object> gatewayPay = new HashMap<>();
            gatewayPay.put("bankCode", "vbank"); // 虚拟银行，专门用于测试环境
            // gatewayPay.put("bankCode", "cmb"); //生产环境
            gatewayPay.put("payType", 1L);
            //gatewayPay.put("bankCardNo", YunClient.encrypt("6228480402637874214")); // 借记卡
            gatewayPay.put("amount", 100);

            // 银联代扣
            HashMap<String, Object> daikouPay = new HashMap<>();
            daikouPay.put("bankCardNo", YunClient.encrypt("6228480318051081871"));
            daikouPay.put("amount", 100);

            // 通联通代扣
            HashMap<String, Object> tltDaikouPay = new HashMap<>();
            tltDaikouPay.put("bankCardNo", YunClient.encrypt("6228480318051081871"));
            tltDaikouPay.put("amount", 10000);


            JSONArray balancePay = new JSONArray();
            JSONObject balancePayObj = new JSONObject();
            balancePayObj.put("accountSetNo", "200001");
            balancePayObj.put("amount", 1);
            balancePay.add(balancePayObj);
            System.out.println("===账户余额 (JSONArray)===   " + balancePay);

            // 组装支付方式
            HashMap<String, Object> payMethod = new HashMap<>();
            // payMethod.put("QUICKPAY", quickPay);
            //payMethod.put("REALNAMEPAY", realnamePay);
            payMethod.put("GATEWAY", gatewayPay);
            // payMethod.put("WITHHOLD_UP", daikouPay);
            // payMethod.put("BALANCE", balancePay);

            request.put("bizUserId", "2018060023");
            request.put("bizOrderNo", System.currentTimeMillis() + "cz");
            request.put("accountSetNo", "200001");
            request.put("amount", 100L);
            request.put("fee", 0L);
            request.put("validateType", 1L);
            request.put("frontUrl", "http://10.55.3.236:6003/gateway/getPayFront.jsp");
            request.put("backUrl", "http://10.55.3.236:6003/test/recievePayBack.jsp");
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
