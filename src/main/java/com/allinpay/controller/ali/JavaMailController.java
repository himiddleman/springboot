package com.allinpay.controller.ali;

import com.allinpay.core.common.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RestController
@Slf4j
public class JavaMailController {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    //发送简单邮件
    @RequestMapping("/mail")
    public ResponseData send() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("15197704032@163.com");
        simpleMailMessage.setTo("462521580@qq.com");
//            simpleMailMessage.setCc(cc);
        simpleMailMessage.setSubject("测试邮件");
        simpleMailMessage.setText("springboot整合邮件");
        javaMailSender.send(simpleMailMessage);
        return ResponseData.success().setData("发送成功");
    }


    //基于thymeleaf模板发送邮件
    @RequestMapping("/htmlMail")
    public ResponseData sendHtml() {
        //获取模板引擎的html
        String emailContent = templateEngine.process("email", new Context());
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("15197704032@163.com");
            helper.setTo("462521580@qq.com");
            helper.setSubject("html测试邮件");
            helper.setText(emailContent, true);
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseData.success().setData("发送成功");
    }

    //发送邮件时添加附件
    @RequestMapping("/attachedMail")
    public ResponseData sendAttachment() {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("15197704032@163.com");
            helper.setTo("462521580@qq.com");
            helper.setSubject("html测试邮件");
            helper.setText("这封邮件有附件的");
            helper.addAttachment("图片.jpg", new ClassPathResource("/static/img/1.jpg"));
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseData.success().setData("发送成功");
    }

    //读取静态资源文件并返回浏览器
    @RequestMapping("/download")
    public void download(HttpServletResponse response) {
        try {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;fileName=" + "1.jpg");
            ServletOutputStream out = response.getOutputStream();
            Resource resource = new ClassPathResource("/static/img/1.jpg");
            InputStream in = resource.getInputStream();
            byte[] bytes = new byte[1024];
            int len;
            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
