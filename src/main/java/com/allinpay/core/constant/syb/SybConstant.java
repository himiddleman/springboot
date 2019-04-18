package com.allinpay.core.constant.syb;

public class SybConstant {
    /**
     * 网关支付
     */
    public static final String CUSID = "990440148166000";//收银宝分配商户号
    public static final String APPID = "00000003";//收银宝分配appid
    public static final String APPKEY = "a0ea3fa20dbd7bb4d5abf1d59d63bae8";//收银宝分配key
    public static final String HOSTNAME = "https://vsp.allinpay.com/apiweb/gateway";//生产地址
    public static final String NOTFIY_URL = "http://baidu.com";//交易结果通知地址 -- 用于入账操作  此处用百度进行测试
    public static final String RET_URL = "http://baidu.com";//交易完成跳转页面地址--用户交易成功跳转 此处用百度进行测试
    public static final int VALID_TIME = 30;//支付有效时间,单位分钟

    /**
     * 网上收银统一下单
     */
    public static final String SYB_CUSID = "990440148166000";
    public static final String SYB_APPID = "00000003";
    public static final String SYB_APPKEY = "a0ea3fa20dbd7bb4d5abf1d59d63bae8";
    public static final String SYB_APIURL = "https://vsp.allinpay.com/apiweb/unitorder";//生产环境

}
