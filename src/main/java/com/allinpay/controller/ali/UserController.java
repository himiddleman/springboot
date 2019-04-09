package com.allinpay.controller.ali;

import com.allinpay.core.common.ResponseData;
import com.allinpay.repository.domain.Admin;
import com.allinpay.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/getAdmin")
    public ResponseData getAdmin(@RequestParam("email") String email,
                                 @RequestParam("password") String password) {
        log.info("email:{} password:{}", email, password);
        Admin admin = userService.getAdmin(email, password);
        if (admin != null) {
            return new ResponseData().success(admin);
        } else {
            return new ResponseData().failure(null);
        }
    }
}
