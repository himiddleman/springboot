package com.allinpay.controller;

import com.allinpay.core.common.ResponseData;
import com.allinpay.core.constant.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * md5 服务调用controller
 */
@Controller
@RequestMapping("/encrypt")
public class EncryptController {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 服务调用 参数为string
     */
    @RequestMapping("/get")
    @ResponseBody
    public ResponseData call() {
        String id = "101";
        String nickname = "hello";
        String admin = restTemplate.getForObject(CommonConstant.CORAL_ADDRESS + "/callservice/testGet?id={id}&name={nickname}",
                String.class, id, nickname);
        return new ResponseData().success(admin);
    }

    /**
     * 服务调用 参数为实体
     * queryParams(new MultivaluedMapImpl()).
     */
    @RequestMapping("/post")
    @ResponseBody
    public String call2() {
        return null;
    }
}
