package com.allinpay.controller.yunst.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.controller.yunst.util.UrlConsts;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

import java.util.HashMap;

/**
 * 4.2.7 批量托管代付（标准版）
 *
 * @author Administrator
 */
public class BatchAgentPayTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "batchAgentPay");

        try {

            // 批量托管代付列表
            JSONArray batchPayList = new JSONArray();

            JSONArray collectPayList1 = new JSONArray();
            HashMap<String, Object> pay1 = new HashMap<>();
            pay1.put("bizOrderNo", System.currentTimeMillis() + "cz");


            HashMap<String, Object> collect1 = new HashMap<>();
            collect1.put("bizOrderNo", "1557746960761cz");
            collect1.put("amount", 10L);
            collectPayList1.add(new JSONObject(collect1));

            pay1.put("collectPayList", collectPayList1);
            pay1.put("bizUserId", "2018060021");
            pay1.put("accountSetNo", "200001");
            pay1.put("backUrl", UrlConsts.BACKURL);
            pay1.put("amount", 10L);
            pay1.put("fee", 0L);

            //====================================================
            HashMap<String, Object> pay2 = new HashMap<>();
            pay2.put("bizOrderNo", System.currentTimeMillis() + "cz");

            JSONArray collectPayList2 = new JSONArray();
            HashMap<String, Object> collect2 = new HashMap<>();
            collect2.put("bizOrderNo", "1557746996969cz");
            collect2.put("amount", 10L);
            collectPayList2.add(new JSONObject(collect2));

            pay2.put("collectPayList", collectPayList2);
            pay2.put("bizUserId", "2018060021");
            pay2.put("accountSetNo", "200001");
            pay2.put("backUrl", UrlConsts.BACKURL);
            pay2.put("amount", 10L);
            pay2.put("fee", 0L);

            batchPayList.add(new JSONObject(pay1));
            batchPayList.add(new JSONObject(pay2));

            request.put("bizBatchNo", System.currentTimeMillis() + "cz");
            request.put("batchPayList", batchPayList);
//            request.put("goodsType", 1L);
//            request.put("goodsNo", "A0001");
            request.put("tradeCode", "2003");

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
