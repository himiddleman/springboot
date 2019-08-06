package com.allinpay.controller.ali;

import com.allinpay.core.common.ResponseData;
import com.allinpay.repository.domain.User;
import com.allinpay.repository.domain.UserConfig;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @Autowired
    private UserConfig userConfig;

    @RequestMapping("/hello")
    @RequiresRoles({"admin"})
    @RequiresPermissions("user:hello")
    @ResponseBody
    public ResponseData sayHello() {
        return ResponseData.success().setData("8080");
    }

    @RequestMapping("/sayhello")
    public ResponseData sayhello() {
        User user = new User();
        BeanUtils.copyProperties(userConfig, user);
        return ResponseData.success().setData(user);
    }

    @RequestMapping("/test/globalException")
    public ResponseData globalException() {
        int i = 1 / 0;
        return ResponseData.success().setData(null);
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
