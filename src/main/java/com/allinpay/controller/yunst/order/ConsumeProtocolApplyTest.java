package com.allinpay.controller.yunst.order;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.controller.yunst.util.UrlConsts;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.text.SimpleDateFormat;

public class ConsumeProtocolApplyTest {
    private static final Logger logger = Logger.getLogger(ConsumeProtocolApplyTest.class);

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "consumeProtocolApply");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {


            request.put("payerId", "2018060023");
            request.put("protocolNo", "1044874671775813632");
            request.put("recieverId", "zzh001");
            request.put("bizOrderNo", "zzh00001");

            // 支付方式  仅支持余额
            JSONArray balancePay = new JSONArray();
            JSONObject balancePayObj = new JSONObject();
            balancePayObj.put("accountSetNo", "100000");
            balancePayObj.put("amount", 1);
            balancePay.add(balancePayObj);

            // 组装支付方式
            JSONObject payMethod = new JSONObject();
            payMethod.put("BALANCE", balancePay);
            System.out.println(payMethod);
//			System.out.println( payMethod.isNull("BALANCE") );
//			System.out.println( payMethod.length()!= 1 );
            request.put("payMethod", payMethod);
            request.put("amount", 1L);
            request.put("fee", 0L);
			
		/*	JSONArray splitRule = new JSONArray();
			JSONArray splitRule3 = new JSONArray();

			//一级分账
			JSONObject splistRule1 = new JSONObject();
			splistRule1.put("bizUserId", "testA");
			splistRule1.put("amount", 6L);
			splistRule1.put("fee", 1L);
			splistRule1.put("remark", "aaaaa");

			splitRule.put(splistRule1);
			System.out.println("===一级分账===   "+splitRule);

			JSONArray splitRule1List = new JSONArray();
			//二级分账
			JSONObject splistRule2 = new JSONObject();
			splistRule2.put("bizUserId", "testB");
			splistRule2.put("amount", 6L);
			splistRule2.put("fee", 1L);
			splistRule2.put("remark", "aaaaa");
			
			
			splitRule1List.put(splistRule2);
			splistRule1.put("splitRuleList", splitRule1List);
			
			splitRule3.put(splistRule1);
			System.out.println("===二级分账===   "+splitRule);	
			
			
			//request.put("splitRule", splitRule);
*/
            request.put("backUrl", UrlConsts.BACKURL);
            request.put("ordErexpireDatetime", "");//yyyy-MM-dd HH:mm:ss 订单最长时效为24小时，默认为最长时效
            request.put("goodsName", "");
            request.put("goodsDesc", "");
            request.put("industryCode", "1010");
            request.put("industryName", "保险代理");
            request.put("source", "2");
            request.put("summary", "");
            request.put("extendInfo", "");


            String res = YunClient.request(request);
            logger.info("res: " + res);


        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
