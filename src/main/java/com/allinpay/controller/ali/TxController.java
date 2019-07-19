package com.allinpay.controller.ali;

import com.allinpay.core.common.ResponseData;
import com.allinpay.repository.domain.permission.Admin;
import com.allinpay.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tx")
public class TxController {
    @Autowired
    private IUserService userService;

    @RequestMapping("test")
    public ResponseData test() {
        Admin admin = new Admin();
        admin.setKid(100);
        admin.setNickname("tg");
        admin.setEmail("tg@allinpay.com");
        admin.setPassword("123456");
        userService.addUser(admin);
        return ResponseData.success().setData(null);
    }
}
