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

    /**
     * 当面付订单支付
     */
    public static final String CUSPAY_CUSID = "55133106513C1MJ";
    public static final String CUSPAY_APPID = "00143572";
    public static final String CUSPAY_APPKEY = "allinpay888";
    public static final String CUSPAY_URL = "https://syb.allinpay.com/sappweb/usertrans/cuspay";

    /**
     * 付款
     */
    public static final String PAY_CUSID = "990581007426001";
    public static final String PAY_APPID = "00000051";
    public static final String PAY_APPKEY = "allinpay888";
    public static final String PAY_URL = "https://vsp.allinpay.com/apiweb/payment/singlepay";

    /**
     * 银行账户验证
     */
    public static final String BANK_CUSID = "990440153996000";
    public static final String BANK_APPID = "00000000";
    public static final String BANK_APPKEY = "43df939f1e7f5c6909b3f4b63f893994";
    public static final String BANK_URL = "https://vsp.allinpay.com/apiweb/verify/";

    /**
     * H5
     */
    public static final String H5_URL = "https://syb.allinpay.com/apiweb/h5unionpay/";

    /**
     * 快捷支付
     */
    public static final String QUICKPAY_URL = "http://test.allinpaygd.com/apiweb/qpay";//测试外网
    public static final String QUICKPAY_CUSID = "990581053996001";
    public static final String QUICKPAY_APPID = "00000156";
    public static final String QUICKPAY_APPKEY = "43df939f1e7f5c6909b3f4b63f893994";
//    public static final String QUICKPAY_URL = "https://vsp.allinpay.com/apiweb/qpay";//生产
//    public static final String QUICKPAY_CUSID = "55133804816M9YQ";
//    public static final String QUICKPAY_APPID = "00167711";
//    public static final String QUICKPAY_APPKEY = "dsafd3dxe2";

    /**
     * 预支付
     */
    public static final String PREPAY_URL = "https://vsp.allinpay.com/apiweb/prescanpay";
    public static final String PREPAY_CUSID = "990440148166000";
    public static final String PREPAY_APPID = "00000003";
    public static final String PREPAY_APPKEY = "a0ea3fa20dbd7bb4d5abf1d59d63bae8";


    /**
     * 合作方进件
     */
    public static final String SYB_MCHAPI_URL = "https://test.allinpaygd.com/vsppcusapi/merchantapi";
    public static final String SYB_MCHAPI_CUSID = "55058100780004S";
    public static final String SYB_MCHAPI_APPID = "00000005";
    public static final String SYB_MCHAPI_APPKEY = "123456";
}
