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


            request.put("accountSetNo", "");


            String res = YunClient.request(request);
            logger.info("res: " + res);

            JSONObject resp = JSON.parseObject(res);
            logger.info("status=[" + resp.getString("status") + "]");
            logger.info("signedValue=[" + resp.getString("signedValue") + "]");
            logger.info("sign=[" + resp.getString("sign") + "]");
            logger.info("errorCode=[" + resp.getString("errorCode") + "]");
            logger.info("message=[" + resp.getString("message") + "]");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
