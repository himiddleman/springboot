package com.allinpay.controller.ali;

import com.allinpay.core.common.ResponseData;
import com.allinpay.repository.domain.permission.Admin;
import com.allinpay.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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
        Admin admin = userService.getAdmin(email, password);
        if (admin != null) {
            return ResponseData.success().setData(admin);
        } else {
            return ResponseData.failure("110", "101").setData(null);
        }
    }

    @RequestMapping("/getOne")
    public ResponseData getAdmin(Admin admin) {
        admin.setCreateTime(new Date());
        admin.setUpdateTime(new Date());
        return ResponseData.success().setData(admin);
    }
}
