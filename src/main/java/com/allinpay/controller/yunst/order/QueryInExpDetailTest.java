package com.allinpay.controller.yunst.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

/**
 * 4.2.21 查询账户收支明细
 *
 * @author Administrator
 */
public class QueryInExpDetailTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "queryInExpDetail");

        try {
            request.put("bizUserId", "tg0505");
            request.put("accountSetNo", "200001");
            request.put("DateStart", "2019-05-09");
            request.put("DateEnd", "2019-05-10");
            request.put("startPosition", 1L);
            request.put("queryNum", 10L);

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
