/**
 * create this file at 上午10:41:50 by renhd.
 */
package com.allinpay.controller.yunst2.viporder;

import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.PrivRequest;
import org.junit.Test;

/**
 * @author 任海东 2019年2月25日
 */
public class VipGetOrderPaymentDetailTest {

    // 特权会员订单查询接口特殊一点 需要使用PrivRequest
    @Test
    public void test() {
        final PrivRequest request = new PrivRequest("OrderService", "vipGetOrderPaymentDetail");
        request.setPrivBizUserId("6a20dbb8-8205-44a4-8991-b19cacc41ef5");
        request.put("sysid", "1902141108045931770");
        request.put("bizOrderNo", "1551063066003lpdp");
        try {
            System.out.println(YunClient.request(request));
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

}
