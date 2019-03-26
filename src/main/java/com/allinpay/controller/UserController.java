package com.allinpay.controller;

import com.allinpay.core.common.ResponseData;
import com.allinpay.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/getAdmin")
    public ResponseData getAdmin(@RequestParam("email") String email,
                                 @RequestParam("password") String password) {
        log.info("email:{} password:{}", email, password);
        return new ResponseData().success(userService.getAdmin(email, password));
    }
}
