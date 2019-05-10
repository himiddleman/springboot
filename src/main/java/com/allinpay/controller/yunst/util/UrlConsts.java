package com.allinpay.controller.yunst.util;

/**
 * 所有地址常量�?
 * 注意IP和端口的替换116.228.64.55:9092=192.168.14.70:8080
 * <li>1�?92.168.14.70:8080为通联内网地址IP和端�?/li>
 * <li>2�?16.228.64.55:9092为通联外网地址IP和端�?/li>
 * <li>3、yun.allinpay.com为云商通生产环境域名，端口80，需将http替换为https模式</li>
 * <li>4�?92.168.14.65:8080为通联内网测试环境地址IP和端�?/li>
 *
 * @author Administrator
 */
public class UrlConsts {

    /**
     * 重置密码界面
     */
    public static final String RESETPAYPWD = "http://116.228.64.55:9092/pwd/resetPayPwd.html?";

    /**
     * 测试跳转界面
     */
    public static final String TESTFRONT = "http://116.228.64.55:9092/testFront.jsp";

    /**
     * 回调通知界面
     */
    public static final String BACKURL = "http://www.baidu.com";

    /**
     * 设置密码
     */
    public static final String SETPAYPWD = "http://116.228.64.55:9092/pwd/setPayPwd.html?";


    public static final String UPDATEPAYPWD = "http://116.228.64.55:9092/pwd/updatePayPwd.html?";


    /**
     * 签约协议
     */
    public static final String SIGNCONTRACT = "http://116.228.64.55:9092/yungateway/member/signContract.html?";
    /**
     * 账户余额签约协议
     */
    public static final String SIGNBALANCEPROTOCOL = "http://116.228.64.55:9092/yungateway/member/signBalanceProtocol.html";
    /**
     * 通过支付密码修改手机�?
     */
    public static final String UPDATEPHONEBYPAYPWD = "http://116.228.64.55:9092/pwd/updatePhoneByPayPwd.html?";
    /**
     * 接受支付测试地址
     */
    public static final String RECIEVEPAYBACK = "http://116.228.64.55:9092/test/recievePayBack.jsp";
    /**
     * 网关支付跳转
     */
    public static final String GETPAYFRONT = "http://116.228.64.55:9092/gateway/getPayFront.jsp";
    /**
     * 前台确认支付
     */
    public static final String FRONTTRANS = "http://116.228.64.55:9092/service/gateway/frontTrans.do?";
    /**
     * 前台确认支付
     */
    public static final String FRONTPWDTRANS = "http://116.228.64.55:9092/yungateway/pwd/payOrder.html?";

}
