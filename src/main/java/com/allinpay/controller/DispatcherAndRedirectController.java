package com.allinpay.controller;

import com.allinpay.core.util.WebServiceUtil;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.core.MediaType;

/**
 * 测试转发，重定向，服务调用
 * 使用md5
 */
@Controller
@RequestMapping("/jump")
public class DispatcherAndRedirectController {
    /**
     * 转发
     */
    @RequestMapping("/forward")
    public String forward() {
        return "login";
    }

    /**
     * 单应用重定向
     */
    @RequestMapping("/redirect")
    public String redirect() {
        return "redirect:http://www.baidu.com";
    }

    /**
     * 多应用重定向
     * 页面->服务器A->服务器B
     * 服务器A完成重定向
     * <p>
     * 在服务器B完成重定向是行不通的
     */
    @RequestMapping("/redirect1")
    public String redirect1() {
        String msg;
        try {
            msg = WebServiceUtil.getWebResource().path("/jump/test1").queryParams(new MultivaluedMapImpl()).type(MediaType.APPLICATION_JSON).post(String.class, "hello world");
            System.out.println(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:http://www.baidu.com";
    }
}
