package com.allinpay.controller.ali;

import com.allinpay.core.common.ResponseData;
import com.allinpay.repository.domain.User;
import com.allinpay.repository.domain.UserConfig;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.example.demo.service.IHelloService;

@Controller
public class HelloController {
    @Autowired
    private UserConfig userConfig;
//    @Autowired
//    private IHelloService helloService;

//    @RequestMapping("/sayHello")
//    public void helloworld() {
//        helloService.sayHello();
//    }

    @RequestMapping("/hello")
    @RequiresPermissions("user:update,create")
    @ResponseBody
    public ResponseData sayHello() {
        return ResponseData.success().setData("8080");
    }

    @RequestMapping("/sayhello")
    @RequiresPermissions("user:update")
    public ResponseData sayhello() {
        User user = new User();
        BeanUtils.copyProperties(userConfig, user);
        return ResponseData.success().setData(user);
    }

    @RequestMapping("/test/globalException")
    @RequiresPermissions("user:update:id_1")
    public ResponseData globalException() {
        int i = 1 / 0;
        return ResponseData.success().setData(null);
    }

    @RequestMapping("/cardlist")
    public String cardList() {
        return "other/cardlist";
    }

    @RequestMapping("/carddetail")
    public String cardDetail() {
        return "other/carddetail";
    }

    @RequestMapping("/merchantcard")
    public String merchantCard() {
        return "other/merchantcard";
    }

    @RequestMapping("/coupon")
    public String index() {
        return "other/coupon";
    }

    @RequestMapping("/coupondetail")
    public String couponDetail() {
        return "other/coupondetail";
    }

    @RequestMapping("/2DFire")
    public String twoDFire() {
        return "other/2DFire";
    }

    @RequestMapping("/partner/easylife")
    public String freedomLife() {
        return "other/easylife";
    }
}
