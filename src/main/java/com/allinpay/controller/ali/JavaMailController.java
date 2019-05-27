package com.allinpay.controller.ali;

import com.allinpay.core.common.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@Slf4j
public class JavaMailController {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @RequestMapping("/mail")
    public ResponseData send() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("15197704032@163.com");
        simpleMailMessage.setTo("501178662@qq.com");
//            simpleMailMessage.setCc(cc);
        simpleMailMessage.setSubject("测试邮件");
        simpleMailMessage.setText("springboot整合邮件");
        javaMailSender.send(simpleMailMessage);
        return new ResponseData().success("发送成功");
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
            helper.setTo("501178662@qq.com");
            helper.setSubject("html测试邮件");
            helper.setText(emailContent, true);
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseData().success("发送成功");
    }

    //发送邮件时添加附件
    @RequestMapping("/attachedMail")
    public ResponseData sendAttachment() {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("15197704032@163.com");
            helper.setTo("501178662@qq.com");
            helper.setSubject("html测试邮件");
            helper.setText("这封邮件有附件的");
            helper.addAttachment("图片.jpg", new File(ResourceUtils.getURL("classpath:").getPath() + "/static/img/1.jpg"));
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData().success("发送成功");
    }

    //读取静态资源文件并返回浏览器
    @RequestMapping("/download")
    public void download(HttpServletResponse response) {
        try {
            File file = new File(ResourceUtils.getURL("classpath:").getPath() + "/static/img/1.jpg");
            log.info("文件是否存在：" + file.isFile());
            InputStream inputStream = new FileInputStream(file);
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + "a.jpg");
            ServletOutputStream outputStream = response.getOutputStream();
            int count;
            while ((count = inputStream.read()) != -1) {
                outputStream.write(count);
            }
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/path")
    public ResponseData path(HttpServletResponse response) {
        try {
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + "1.jpg");
            ServletOutputStream out = response.getOutputStream();
            Resource resource = new ClassPathResource("/static/img/1.jpg");
//            log.info("文件是否存在：" + resource.getFile().isFile());
            InputStream in = resource.getInputStream();
            byte[] bytes = new byte[1024];
            while (in.read(bytes) != -1) {
                out.write(bytes);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseData().success("发送成功");
    }
}
