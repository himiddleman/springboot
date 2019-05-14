package com.allinpay.controller.yunst.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

/**
 * 4.2.14 查询、修改商品
 *
 * @author Administrator
 */
public class QueryModifyGoodsTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "queryModifyGoods");

        try {
            request.put("bizUserId", "tg0505");
            request.put("bizGoodsNo", "A0006");
            request.put("goodsType", 2L);
//            request.put("beginDate", "2018-06-12");
//            request.put("endDate", "2028-06-12");

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
