package com.allinpay.controller.yunst3.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

import java.util.HashMap;

public class EntryGoodsTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "entryGoods");

        try {
            request.put("bizUserId", "WHYGR2019001");
            request.put("goodsType", 5L);
            request.put("bizGoodsNo", "WHYGOODS002");
            request.put("goodsName", "跨境商品");
            request.put("goodsDetail", "跨境商品");

            HashMap<String, Object> goodsParams = new HashMap<>();
            goodsParams.put("eshopEntCode", "eshopEntCode");
            goodsParams.put("eshopEntName", "eshopEntName");
            goodsParams.put("customsCode", "HG001");

            request.put("goodsParams", goodsParams);
            request.put("showUrl", "http://www.baidu.com");

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
