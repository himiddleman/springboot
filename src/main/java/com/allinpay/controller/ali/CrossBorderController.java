package com.allinpay.controller.ali;

import com.allinpay.core.util.syb.MD5Util;
import com.sun.jersey.core.util.Base64;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;

@RestController
public class CrossBorderController {
    @Autowired
    private RestTemplate restTemplate;
    private String REQ_URL = "http://ceshi.allinpay.com/customs/pvcapply";

    public static void main(String[] args) throws UnsupportedEncodingException {
        XStream xStream = new XStream(new Xpp3Driver(new NoNameCoder()));
        Class[] clazz = {PaymentInfo.class, RequestHead.class, RequestBody.class};
        xStream.processAnnotations(clazz);


        RequestBody body = new RequestBody();
        String bodyXml = xStream.toXML(body);
        System.out.println(bodyXml);
        //报文格式化，xml的回车空格换行处理
        String formatBodyXml = bodyXml.replaceAll("\\s*|\r|\n", "");
        System.out.println(formatBodyXml);
        //报文md5加密
        String signSrc = formatBodyXml + "<key>1234567890</key>";
        String signMsg = MD5Util.md5(signSrc);

        RequestHead head = new RequestHead();
        head.setSignMsg(signMsg);
        System.out.println(signSrc);

        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setHead(head);
        paymentInfo.setBody(body);
        String xml = xStream.toXML(paymentInfo).replaceAll("\\s*|\r|\n", "");
        System.out.println(xml);
        //整体传输参数,base64jar包选择
        byte[] verificationTextByte = Base64.encode(xml.getBytes("utf-8"));
        String parameterValue = new String(verificationTextByte, "utf-8");
        System.out.println(parameterValue);
    }

    @RequestMapping("cross")
    public String crossBorder() throws UnsupportedEncodingException {
        XStream xStream = new XStream(new Xpp3Driver(new NoNameCoder()));
        Class[] clazz = {PaymentInfo.class, RequestHead.class, RequestBody.class};
        xStream.processAnnotations(clazz);


        RequestBody body = new RequestBody();
        String bodyXml = xStream.toXML(body);
        System.out.println(bodyXml);
        //报文格式化，xml的回车空格换行处理
        String formatBodyXml = bodyXml.replaceAll("\\s*|\r|\n", "");
        System.out.println(formatBodyXml);
        //报文md5加密
        String signSrc = formatBodyXml + "<key>1234567890</key>";
        String signMsg = MD5Util.md5(signSrc).toUpperCase();

        RequestHead head = new RequestHead();
        head.setSignMsg(signMsg);
        System.out.println(signSrc);

        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setHead(head);
        paymentInfo.setBody(body);
        String xml = xStream.toXML(paymentInfo).replaceAll("\\s*|\r|\n", "");
        System.out.println(xml);
        //整体传输参数,base64jar包选择
        byte[] verificationTextByte = Base64.encode(xml.getBytes("utf-8"));
        String parameterValue = new String(verificationTextByte, "utf-8");
        System.out.println(parameterValue);

        //请求支付报关接口
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("data", parameterValue);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> signedResult = restTemplate.postForEntity(REQ_URL, request, String.class);
        String result = new String(Base64.decode(signedResult.getBody()));
        //异常情况输出：RETURN_CODE=0005&RETURN_MSG=必输项为空-版本号
        //正常情况输出xml格式化字符串
        System.out.println(result);
        return result;
    }
}

@Data
@XStreamAlias("PAYMENT_INFO")
class PaymentInfo {
    @XStreamAlias("HEAD")
    private RequestHead head = new RequestHead();
    @XStreamAlias("BODY")
    private RequestBody body = new RequestBody();
}

@Data
@XStreamAlias("HEAD")
class RequestHead {
    @XStreamAlias("VERSION")
    private String version = "v5.6";
    @XStreamAlias("VISITOR_ID")
    private String visitorId = "MCT";
    @XStreamAlias("MCHT_ID")
    private String mchtId = "109123456123456";
    @XStreamAlias("ORDER_NO")
    private String orderNo = "20170701002004";
    @XStreamAlias("TRANS_DATETIME")
    private String transDateTime = "20170601120816";
    @XStreamAlias("CHARSET")
    private String charset = "1";
    @XStreamAlias("SIGN_TYPE")
    private String signType = "1";
    @XStreamAlias("SIGN_MSG")
    private String signMsg = "CDD170328587DB8075F6198448D2BECB";
}

@Data
@XStreamAlias("BODY")
class RequestBody {
    @XStreamAlias("CUSTOMS_CODE")
    private String customsCode = "HG023";
    @XStreamAlias("PAYMENT_CHANNEL")
    private String paymentChannel = "2";
    @XStreamAlias("CUS_ID")
    private String cusId = "8215810949800UR";
    @XStreamAlias("PAYMENT_DATETIME")
    private String paymentDateTime = "20170601120816";
    @XStreamAlias("MCHT_ORDER_NO")
    private String mchtOrderNo = "NO20170705102740";
    @XStreamAlias("PAYMENT_ORDER_NO")
    private String getPaymentOrderNo = "T2017080945678999";
    @XStreamAlias("PAYMENT_AMOUNT")
    private String getPaymentAmount = "10000";
    @XStreamAlias("CURRENCY")
    private String currency = "156";
    @XStreamAlias("PAYER_NAME")
    private String payerName = "张三";
    @XStreamAlias("PAPER_TYPE")
    private String payerType = "01";
    @XStreamAlias("PAPER_NUMBER")
    private String payerNumber = "453722198808098231";
    @XStreamAlias("PAPER_PHONE")
    private String payerPhone = "13888888888";
    @XStreamAlias("PAPER_EMAIL")
    private String payerEmail = "zhangsan@163.com";
    //无值时使用空字符串输出空标签
//    private String payerEmail = "";
}
