package com.allinpay.controller.yunst.merchant;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.controller.yunst.util.UrlConsts;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.text.SimpleDateFormat;

/**
 * 4.3.4 平台头寸调拔充值（通联通上账）
 *
 * @author Administrator
 */
public class PlatformPulloutTest {
    private static final Logger logger = Logger.getLogger(PlatformPulloutTest.class);

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("MerchantService", "platformPullout");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {


            request.put("bizOrderNo", "0001");
            request.put("amount", "1");
            request.put("backUrl", UrlConsts.BACKURL);
            request.put("source", "");
            request.put("summary", "");
            request.put("extendInfo", "");


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
