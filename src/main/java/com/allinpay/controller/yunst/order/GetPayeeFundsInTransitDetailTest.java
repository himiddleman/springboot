package com.allinpay.controller.yunst.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 4.2.23 收款方在途资金明细查询
 *
 * @author Administrator
 */
@Slf4j
public class GetPayeeFundsInTransitDetailTest {

    @Test
    public void getPayeeFundsInTransitDetailTest() {

        final YunRequest request = new YunRequest("OrderService", "getPayeeFundsInTransitDetail");

        try {
            request.put("bizOrderNo", "1557746960761cz");
//            request.put("accountSetNo", "");
            request.put("bizUserId", "2018060022");
//            request.put("dateStart", "2018-09-20");//yyyy-MM-dd，托管代付时间
//            request.put("dateEnd", "2018-09-20");

            String res = YunClient.request(request);

            JSONObject resp = JSON.parseObject(res);
            log.info("status=[" + resp.getString("status") + "]");
            log.info("signedValue=[" + resp.getString("signedValue") + "]");
            log.info("sign=[" + resp.getString("sign") + "]");
            log.info("errorCode=[" + resp.getString("errorCode") + "]");
            log.info("message=[" + resp.getString("message") + "]");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
