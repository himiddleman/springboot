package com.allinpay.controller.yunst.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.controller.yunst.util.UrlConsts;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 4.2.3 提现申请
 *
 * @author Administrator
 */
public class WithdrawApplyTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("OrderService", "withdrawApply");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 15);
        Date date = calendar.getTime();
        String ordErexpireDatetime = sdf.format(date);

        try {
            request.put("bizUserId", "2018060023");
            request.put("bizOrderNo", System.currentTimeMillis() + "cz1");
            request.put("accountSetNo", "200001");
            request.put("amount", 1L);
            request.put("fee", 0L);
            request.put("validateType", 2L);
            request.put("backUrl", UrlConsts.RECIEVEPAYBACK);
            request.put("ordErexpireDatetime", ordErexpireDatetime);
            //	request.put("bankCardNo", YunClient.encrypt("6228480402637874221"));
            //	request.put("bankCardPro", 0L);
            request.put("withdrawType", "T0");
            request.put("industryCode", "1010");
            request.put("industryName", "保险代理");
            request.put("source", 2L);
            request.put("summary", "withdraw");
            request.put("extendInfo", "this is extendInfo");

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
