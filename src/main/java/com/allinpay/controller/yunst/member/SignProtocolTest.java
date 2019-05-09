package com.allinpay.controller.yunst.member;

import com.allinpay.controller.yunst.util.UrlConsts;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

public class SignProtocolTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("MemberService", "signBalanceProtocol");

        String url = UrlConsts.SIGNBALANCEPROTOCOL;
        try {
            request.put("protocolReqSn", "0110");
            request.put("payerId", "zzh001");
            request.put("recieverId", "allYunBizUserId");
            request.put("protocolName", "wwww");
            request.put("jumpUrl", UrlConsts.BACKURL);//前台跳转地址
            request.put("backUrl", UrlConsts.BACKURL);//后台通知地址
            request.put("source", UrlConsts.BACKURL);

            String res = YunClient.encodeTwice(request);

            /*			https://www.baidu.com/?sign=QNIjwe0CSTqUJpStrDLDMmyZATSC6E5yiaVxdTvwQkwLppG8jkUUcZ%2FutlZOnC6HbTpFBJ%2BDuYcGpS85EH3HPIAED%2BNm%2FpcXoS2kN1iIjkohhOxVyZepSBViGImG6awMyCJmMNqeHOZkK1W%2F0CQ81q4fLHnxT0bnLRBL2IHWcRo%3D&status=OK&signedValue=%7B%22protocolReqSn%22%3A%220110%22%2C%22signStatus%22%3A%22success%22%2C%22protocolNo%22%3A1044874671775813632%7D
             */

            System.out.println("res: " + url + "?" + res);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
