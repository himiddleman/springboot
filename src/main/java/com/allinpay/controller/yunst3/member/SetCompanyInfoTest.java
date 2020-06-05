package com.allinpay.controller.yunst3.member;

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
            request.put("bizUserId", "yyyy");
            request.put("backUrl", "http://www.baidu.com");
            request.put("isAuth", false);

            // 组companyBasicInfo企业信息jsonobject对象
            JSONObject companyBasicInfo = new JSONObject();
            companyBasicInfo.put("companyName", "11");
            companyBasicInfo.put("companyAddress", "222");
            companyBasicInfo.put("authType", 2L);//1-三证，2-一证
            companyBasicInfo.put("uniCredit", "111111");
            companyBasicInfo.put("businessLicense", "222222");
            companyBasicInfo.put("organizationCode", "333333");
            companyBasicInfo.put("taxRegister", "444444");
            companyBasicInfo.put("expLicense", "2023-1-1");
            companyBasicInfo.put("telephone", "15197704032");
            companyBasicInfo.put("legalName", "李德坚");
            companyBasicInfo.put("identityType", 1L);
            companyBasicInfo.put("legalIds", RSAUtil.encrypt("441423198601101719"));
            companyBasicInfo.put("legalPhone", "15197704033");//6228481000000051211
            companyBasicInfo.put("accountNo", RSAUtil.encrypt("6227003010200537444"));
            companyBasicInfo.put("parentBankName", "建设银行");
//			companyBasicInfo.put("bankCityNo", "777777");
            companyBasicInfo.put("bankName", "中国建设银行股份有限公司益阳赫山支行");
            companyBasicInfo.put("unionBank", "105561015137");
//			companyBasicInfo.put("province", "浙江");
//			companyBasicInfo.put("city", "杭州");

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
