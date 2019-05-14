package com.allinpay.controller.yunst.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.SimpleDateFormat;

/**
 * 4.2.22 付款方资金代付明细查询
 *
 * @author Administrator
 */
@Slf4j
public class GetPaymentInformationDetailTest {
//    private static final Logger logger = Logger.getLogger(GetPaymentInformationDetailTest.class);

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "getPaymentInformationDetail");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {


            request.put("bizOrderNo", "1557746960761cz");
//            request.put("accountSetNo", "200001");
//            request.put("bizUserId", "2018060020");
//            request.put("dateStart", "2019-05-10");//yyyy-MM-dd，托管代付时间
//            request.put("dateEnd", "2019-05-20");


            String res = YunClient.request(request);
            log.info("res: " + res);

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
