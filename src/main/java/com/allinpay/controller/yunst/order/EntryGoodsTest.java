package com.allinpay.controller.yunst.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.controller.yunst.util.UrlConsts;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

import java.util.HashMap;

/**
 * 4.2.13 商品录入
 *
 * @author Administrator
 */
public class EntryGoodsTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "entryGoods");

        try {
            request.put("bizUserId", "2018060023");
            request.put("goodsType", 1L);
            request.put("bizGoodsNo", "A0006");
            request.put("goodsName", "插标卖首");
            request.put("goodsDetail", "111222");

            HashMap<String, Object> goodsParams = new HashMap<>();
            goodsParams.put("amount", 10);
            goodsParams.put("totalAmount", 10);
            goodsParams.put("highestAmount", 1);
            goodsParams.put("annualYield", 0.1);
            goodsParams.put("investmentHorizon", 12);
            goodsParams.put("investmentHorizonScale", 2);
            goodsParams.put("beginDate", "");
            goodsParams.put("endDate", "");
            goodsParams.put("repayType", 1);
            goodsParams.put("guaranteeType", 1);
            goodsParams.put("repayPeriodNumber", 1);
            goodsParams.put("minimumAmountInvestment", 1);

            request.put("goodsParams", goodsParams);
            request.put("showUrl", UrlConsts.BACKURL);

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
