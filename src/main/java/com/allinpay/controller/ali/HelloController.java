package com.allinpay.controller.ali;

import com.allinpay.core.common.ResponseData;
import com.allinpay.repository.domain.User;
import com.allinpay.repository.domain.UserConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloController {
    @Autowired
    private UserConfig userConfig;

    @RequestMapping("/hello")
    public ResponseData sayHello() {
        User user = new User();
        user.setAge(18);
        user.setName("小明");
        user.setPassword("123456");
        user.setBirthday(new Date());
        user.setDesc("hahahah");
        return new ResponseData().success(user);
    }

    @RequestMapping("/sayhello")
    public ResponseData sayhello() {
        User user = new User();
        BeanUtils.copyProperties(userConfig, user);
        return new ResponseData().success(user);
    }

    @RequestMapping("/test/globalException")
    public ResponseData globalException() {
        int i = 1 / 0;
        return new ResponseData().success(null);
    }
}
