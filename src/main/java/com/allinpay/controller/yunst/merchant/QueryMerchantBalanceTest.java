package com.allinpay.controller.yunst.merchant;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.text.SimpleDateFormat;

/**
 * 4.1.25 平台账户集余额查询
 *
 * @author Administrator
 */
public class QueryMerchantBalanceTest {
    private static final Logger logger = Logger.getLogger(QueryMerchantBalanceTest.class);

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("MerchantService", "queryMerchantBalance");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {


            request.put("accountSetNo", "2000000");


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
