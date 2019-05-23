package com.allinpay.controller.yunst2.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

public class QueryModifyGoodsTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "queryModifyGoods");

        try {
            request.put("bizUserId", "zhuyqgr");
            request.put("bizGoodsNo", "A0001");
            request.put("goodsType", 1L);
            request.put("beginDate", "2017-06-12");
            request.put("endDate", "2019-06-12");

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
