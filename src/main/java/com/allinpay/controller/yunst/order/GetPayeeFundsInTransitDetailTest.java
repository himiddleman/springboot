package com.allinpay.controller.yunst.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * 4.2.23 收款方在途资金明细查询
 *
 * @author Administrator
 */
public class GetPayeeFundsInTransitDetailTest {
    private static final Logger logger = Logger.getLogger(GetPayeeFundsInTransitDetailTest.class);

    @Test
    public void getPayeeFundsInTransitDetailTest() {

        final YunRequest request = new YunRequest("OrderService", "getPayeeFundsInTransitDetail");

        try {
            request.put("bizOrderNo", "");
            request.put("accountSetNo", "");
            request.put("bizUserId", "");
            request.put("dateStart", "2018-09-20");//yyyy-MM-dd，托管代付时间
            request.put("dateEnd", "2018-09-20");

            String res = YunClient.request(request);

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
