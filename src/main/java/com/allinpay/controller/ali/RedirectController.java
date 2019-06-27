package com.allinpay.controller.ali;

import com.allinpay.core.common.ResponseData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @RequestMapping("/merchantManage")
    public String merchantManage() {
        return "merchantManage";
    }

    @GetMapping("/getList")
    @ResponseBody
    public ResponseData getList(int pageNum, int pageSize, String username) {
        ArrayList arrayList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("id", "001");
        map.put("username", "tg");
        map.put("sex", "男");
        map.put("city", "杭州");
        map.put("sign", "一步一步走");
        map.put("experience", "350000");
        map.put("score", "100");
        map.put("classify", "程序员");
        map.put("wealth", "78900");

        Map<String, String> map2 = new HashMap<>();
        map2.put("id", "002");
        map2.put("username", "tg");
        map2.put("sex", "男");
        map2.put("city", "杭州");
        map2.put("sign", "一步一步走");
        map2.put("experience", "350000");
        map2.put("score", "100");
        map2.put("classify", "程序员");
        map2.put("wealth", "78900");

        Map<String, String> map3 = new HashMap<>();
        map3.put("id", "003");
        map3.put("username", "tg");
        map3.put("sex", "男");
        map3.put("city", "杭州");
        map3.put("sign", "一步一步走");
        map3.put("experience", "350000");
        map3.put("score", "100");
        map3.put("classify", "程序员");
        map3.put("wealth", "78900");

        Map<String, String> map4 = new HashMap<>();
        map4.put("id", "004");
        map4.put("username", "tg");
        map4.put("sex", "男");
        map4.put("city", "杭州");
        map4.put("sign", "一步一步走");
        map4.put("experience", "350000");
        map4.put("score", "100");
        map4.put("classify", "程序员");
        map4.put("wealth", "78900");

        Map<String, String> map5 = new HashMap<>();
        map5.put("id", "005");
        map5.put("username", "tg");
        map5.put("sex", "男");
        map5.put("city", "杭州");
        map5.put("sign", "一步一步走");
        map5.put("experience", "350000");
        map5.put("score", "100");
        map5.put("classify", "程序员");
        map5.put("wealth", "78900");

        Map<String, String> map6 = new HashMap<>();
        map6.put("id", "006");
        map6.put("username", "tg");
        map6.put("sex", "男");
        map6.put("city", "杭州");
        map6.put("sign", "一步一步走");
        map6.put("experience", "350000");
        map6.put("score", "100");
        map6.put("classify", "程序员");
        map6.put("wealth", "78900");

        Map<String, String> map7 = new HashMap<>();
        map7.put("id", "007");
        map7.put("username", "tg");
        map7.put("sex", "男");
        map7.put("city", "杭州");
        map7.put("sign", "一步一步走");
        map7.put("experience", "350000");
        map7.put("score", "100");
        map7.put("classify", "程序员");
        map7.put("wealth", "78900");

        if (pageNum == 1 && pageSize == 2) {
            arrayList.add(map);
            arrayList.add(map2);
        }

        if (pageNum == 2 && pageSize == 2) {
            arrayList.add(map3);
            arrayList.add(map4);
        }

        if (pageNum == 3 && pageSize == 2) {
            arrayList.add(map5);
            arrayList.add(map6);
        }

        if (pageNum == 4 && pageSize == 2) {
            arrayList.add(map7);
        }

        if ("001".equals(username)) {
            arrayList.clear();
            arrayList.add(map);
        }
        return new ResponseData().success(arrayList);
    }

    @PostMapping("/delById")
    @ResponseBody
    public ResponseData getList(String id) {
        return new ResponseData().success(id);
    }

    @PostMapping("/editById")
    @ResponseBody
    public ResponseData editById(String id) {
        return new ResponseData().success(id);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseData add() {
        return new ResponseData().success(null);
    }

    @GetMapping("/getById")
    @ResponseBody
    public ResponseData getById(String id) {
        ArrayList arrayList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("id", "001");
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
