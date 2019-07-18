package com.allinpay.controller.dev;

import com.allinpay.core.common.ResponseData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用于保单支付的controller
 * 需求：
 * 1.保险公司拓展自己的业务，在各学校推销自己的保险订单。
 * 2.需要开发一个H5页面，扫码进入H5页面，填写投保人的具体信息，然后调用H5收银台下单接口完成支付，投保人填写的信息对应支付接口的remark字段。
 * 3.保险公司人员通过下载交易详情获取到下单人员的信息（备注字段），这样就可以对应这些信息来完成保险订单的人工录入。
 * 4.需要维护保险公司的映射信息，维护区域学校班级关联关系做页面的联动处理。这个可以通过一个PC页面由客户经理维护这些数据。原因是因为不同的保险公司对应
 * 不同的H5页面。
 */
@Controller
@RequestMapping("/h5/policy")
public class PolicyPayController {
    /**
     * 扫码进入H5页面
     *
     * @param merchantId
     * @param map
     * @return
     */
    @RequestMapping("/index/{merchantId}")
    public String index(@PathVariable String merchantId,
                        ModelMap map) {
        map.put("merchantId", merchantId);
        //TODO 验证商户信息是否存在，不存在直接返回错误页面
        //查新增保险公司信息表 有状态字段 有效 无效
        //对外显示的商户号和实际商户号有对应关系
        return "policy";
    }

    /**
     * 下单支付
     * @param merchantId
     * @param remark
     * @return
     */
    @RequestMapping("pay")
    @ResponseBody
    public ResponseData pay(String merchantId, String remark) {
        System.out.println(merchantId + ":" + remark);
        //TODO 根据商户号去数据查询商户支付所需要的配置信息
        //将请求参数做加密处理后返回前端页面，从前端页面发起H5支付
        String param = "?appid=00000003&body=谭光&charset=utf-8&cusid=990440148166000&key=a0ea3fa20dbd7bb4d5abf1d59d63bae8&notify_url=www.baidu.com&randomstr=1556029318959&reqsn=shlh110222&returl=www.baidu.com&trxamt=1&version=12&sign=CD4312D1829019EC41B59A0A354272C6";
        return ResponseData.success().setData(param);
    }

    /**
     * 基础数据导入和维护
     * @return
     */
    @RequestMapping("import")
    public String dataImport() {
        return null;
    }
}
