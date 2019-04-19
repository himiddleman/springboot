package com.allinpay.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class MyErrorController implements ErrorController {
    @RequestMapping("/error")
    public String dealAbNormal(HttpServletResponse response) {
        System.out.println(response.getStatus());
        return "404";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
