package com.allinpay.controller.yunst.member;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allinpay.controller.yunst.util.UrlConsts;
import com.allinpay.yunst.sdk.YunClient;
import com.allinpay.yunst.sdk.bean.YunRequest;
import org.junit.Test;

/***
 * 4.1.7 设置企业信息
 * @author Administrator
 *
 */
public class SetCompanyInfoTest {

    @Test
    public void testMethod() {

        final YunRequest request = new YunRequest("MemberService", "setCompanyInfo");

        try {
            request.put("bizUserId", "zzh007");
            request.put("backUrl", UrlConsts.BACKURL);
            request.put("isAuth", false);

            // 组companyBasicInfo企业信息jsonobject对象
            JSONObject companyBasicInfo = new JSONObject();
            companyBasicInfo.put("companyName", "通联");
            companyBasicInfo.put("companyAddress", "浙江省宁波市");
            companyBasicInfo.put("authType", 1L);
            companyBasicInfo.put("uniCredit", "111111");
            companyBasicInfo.put("businessLicense", "222222");
            companyBasicInfo.put("organizationCode", "333333");
            companyBasicInfo.put("taxRegister", "444444");
            companyBasicInfo.put("expLicense", "2020-1-1");
            companyBasicInfo.put("telephone", "555555");
            companyBasicInfo.put("legalName", "zx_company8");
            companyBasicInfo.put("identityType", 1L);
            companyBasicInfo.put("legalIds", YunClient.encrypt("666666"));
            companyBasicInfo.put("legalPhone", "777777");
            companyBasicInfo.put("accountNo", YunClient.encrypt("6228480402637874214"));
            companyBasicInfo.put("parentBankName", "农业银行");
            companyBasicInfo.put("bankCityNo", "777777");
            companyBasicInfo.put("bankName", "农业银行");
            companyBasicInfo.put("unionBank", "103611001012");
            companyBasicInfo.put("bankCityNo", "");
            companyBasicInfo.put("province", "");
            companyBasicInfo.put("city", "");

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
