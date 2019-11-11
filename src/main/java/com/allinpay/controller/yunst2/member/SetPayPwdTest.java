package com.allinpay.controller.yunst2.member;

import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import com.allinpay.yunst.sdk.util.RSAUtil;
import org.junit.Test;

public class SetPayPwdTest {

    @Test
    public void testMethod() {
        //jumpUrl示例：http://www.baidu.com?sysid=1902271423530473681&sign=bYghT4EXE577GPzaNzJQ0rYCYz8UBv6vp4aLVrm4w0nS6BMJFNRYVnoL6fIn1LNiUWeLtk670ZVsjIqCp4ev1jSm0I/yQu58WOr9XRVZBDrUw7Z3ENqik9zFA424rxUDJ3WC3IFTy4eqxuT3qMW1QaPs3LMxFagrgWGAyy1W2js=&signedValue={"result":"OK","bizUserId":"ceshi01"}&status=OK&
        // 宁波商户接入环境
        String webParamUrl = "http://116.228.64.55:6900/yungateway/pwd/setPayPwd.html?";

        // 上海商户接入环境
//        String webParamUrl = "https://fintech.allinpay.com/yungateway/pwd/setPayPwd.html?";

        final YunRequest request = new YunRequest("MemberPwdService", "setPayPwd");

        try {
            request.put("bizUserId", "ceshi01");
            request.put("phone", "15197704032");
            request.put("name", "谭光");
            request.put("identityType", 1L);
            request.put("identityNo", RSAUtil.encrypt("430224199301107219"));
            request.put("jumpUrl", "http://www.baidu.com");
            request.put("backUrl", "http://122.227.225.142:23663/testFront.jsp");

            String res = YunClient.encodeOnce(request);
            webParamUrl += res;
            System.out.println("webParamUrl: " + webParamUrl);

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
