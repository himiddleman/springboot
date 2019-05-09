package com.allinpay.controller.yunst.merchant;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * 4.3.2 平台集合对账下载
 *
 * @author Administrator
 */
public class GetCheckAccountFileTest {
    private static final Logger logger = Logger.getLogger(GetCheckAccountFileTest.class);

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("MerchantService", "getCheckAccountFile");
        try {


            request.put("date", "20180925");//yyyyMMdd
            request.put("fileType", "1");//1-明细 2-汇总 默认为1


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
