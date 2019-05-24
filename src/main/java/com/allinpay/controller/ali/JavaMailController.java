package com.allinpay.controller.ali;

import com.allinpay.core.common.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JavaMailController {
    @Autowired
    private JavaMailSender javaMailSender;

    @RequestMapping("/mail")
    public ResponseData send() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("15197704032@163.com");
        simpleMailMessage.setTo("462521580@qq.com");
//            simpleMailMessage.setCc(cc);
        simpleMailMessage.setSubject("测试邮件");
        simpleMailMessage.setText("springboot整合邮件");
        javaMailSender.send(simpleMailMessage);
        return new ResponseData().success("发送成功");
    }
}
