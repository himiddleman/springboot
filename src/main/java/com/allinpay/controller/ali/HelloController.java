package com.allinpay.controller.ali;

import com.allinpay.core.common.ResponseData;
import com.allinpay.repository.domain.User;
import com.allinpay.repository.domain.UserConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
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

    @RequestMapping("/cardlist")
    public String cardList() {
        return "cardlist";
    }

    @RequestMapping("/carddetail")
    public String cardDetail() {
        return "carddetail";
    }

    @RequestMapping("/merchantcard")
    public String merchantCard() {
        return "merchantcard";
    }

    @RequestMapping("/coupon")
    public String index() {
        return "coupon";
    }

    @RequestMapping("/coupondetail")
    public String couponDetail() {
        return "coupondetail";
    }

    @RequestMapping("/2DFire")
    public String twoDFire() {
        return "2DFire";
    }
}
