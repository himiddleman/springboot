package com.allinpay.controller.yunst2.member;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import com.allinpay.yunst.sdk.util.RSAUtil;
import org.junit.Test;

public class SetCompanyInfoTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("MemberService", "setCompanyInfo");

        try {
            request.put("bizUserId", "qiye01");//WHYQY2019002
            request.put("backUrl", "http://www.baidu.com");
            request.put("isAuth", false);

            // 组companyBasicInfo企业信息jsonobject对象
            JSONObject companyBasicInfo = new JSONObject();
            companyBasicInfo.put("companyName", "通联支付网络服务有限公司");
            companyBasicInfo.put("companyAddress", "浙江省宁波市");
            companyBasicInfo.put("authType", 1L);//1-三证，2-一证
            companyBasicInfo.put("uniCredit", "111111");
            companyBasicInfo.put("businessLicense", "222222");
            companyBasicInfo.put("organizationCode", "333333");
            companyBasicInfo.put("taxRegister", "444444");
            companyBasicInfo.put("expLicense", "2020-1-1");
            companyBasicInfo.put("telephone", "555555");
            companyBasicInfo.put("legalName", "邬海艳");
            companyBasicInfo.put("identityType", 1L);
            companyBasicInfo.put("legalIds", RSAUtil.encrypt("666666"));
            companyBasicInfo.put("legalPhone", "777777");
            companyBasicInfo.put("accountNo", RSAUtil.encrypt("6228481000000051211"));
            companyBasicInfo.put("parentBankName", "农业银行");
            companyBasicInfo.put("bankCityNo", "777777");
            companyBasicInfo.put("bankName", "农业银行");
            companyBasicInfo.put("unionBank", "010300000000");
            companyBasicInfo.put("bankCityNo", "");
            companyBasicInfo.put("province", "上海");
            companyBasicInfo.put("city", "上海");

            request.put("companyBasicInfo", companyBasicInfo);

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
