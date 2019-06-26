package com.allinpay.controller.ali;

import com.allinpay.core.common.ResponseData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/web")
/**
 * 转发控制器
 */
public class RedirectController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/home_etc")
    public String homeEtc() {
        return "home_etc";
    }

    @GetMapping("/getList")
    @ResponseBody
    public ResponseData getList() {
        ArrayList arrayList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("id", "007");
        map.put("username", "tg");
        map.put("sex", "男");
        map.put("city", "杭州");
        map.put("sign", "一步一步走");
        map.put("experience", "350000");
        map.put("score", "100");
        map.put("classify", "程序员");
        map.put("wealth", "78900");
        arrayList.add(map);
        return new ResponseData().success(arrayList);
    }
}
