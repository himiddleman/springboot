package com.allinpay.controller.ali;

import com.allinpay.core.common.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@Slf4j
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

    @RequestMapping("/path")
    public ResponseData path(HttpServletResponse response) {
        try {
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + "1.jpg");
            ServletOutputStream out = response.getOutputStream();
            Resource resource = new ClassPathResource("/static/img/1.jpg");
            log.info("文件是否存在：" + resource.getFile().isFile());
            InputStream in = new FileInputStream(resource.getFile());
//            byte[] bytes = new byte[1024];
            int len;
            while ((len = in.read()) != -1) {
                out.write(len);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseData().success("发送成功");
    }
}
