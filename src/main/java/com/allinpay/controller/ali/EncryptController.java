package com.allinpay.controller.ali;

import com.allinpay.core.common.ResponseData;
import com.allinpay.core.constant.CommonConstant;
import com.allinpay.repository.domain.permission.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * restTemplate的 crud
 */
@Controller
@RequestMapping("/encrypt")
public class EncryptController {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 服务调用 参数为string
     */
    @RequestMapping("/get1")
    @ResponseBody
    public ResponseData get1() {
        String id = "101";
        String retMsg = restTemplate.getForObject(CommonConstant.CORAL_ADDRESS + "/callservice/testGet1/{id}",
                String.class, id);
        return ResponseData.success().setData(retMsg);
    }

    @RequestMapping("/get2")
    @ResponseBody
    public ResponseData get2() {
        String retMsg = restTemplate.getForObject(CommonConstant.CORAL_ADDRESS + "/callservice/testGet2", String.class);
        return ResponseData.success().setData(retMsg);
    }

    @RequestMapping("/get3")
    @ResponseBody
    public ResponseData get3() {
        String id = "101";
        String name = "谭光";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        String retMsg = restTemplate.getForObject(CommonConstant.CORAL_ADDRESS + "/callservice/testGet3/{id}/{name}", String.class, map);
        return ResponseData.success().setData(retMsg);
    }

    @RequestMapping("/get4")
    @ResponseBody
    public ResponseData get4() {
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        String id = "101";
        String name = "谭光";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        String retMsg = restTemplate.getForObject(CommonConstant.CORAL_ADDRESS + "/callservice/testGet4?id={id}&name={name}", String.class, map);
        return ResponseData.success().setData(retMsg);
    }

    /**
     * 服务调用 参数为实体
     * queryParams(new MultivaluedMapImpl()).
     */
    @RequestMapping("/post1")
    @ResponseBody
    public String post1() {
        int id = 101;
        String name = "谭光";
        Admin admin = new Admin();
        admin.setNickname(name);
        admin.setKid(id);
        String retMsg = restTemplate.postForObject(CommonConstant.CORAL_ADDRESS + "/callservice/testPost1/{id}", admin,
                String.class, id);
        return retMsg;
    }

    @RequestMapping("/post2")
    @ResponseBody
    public String post2() {
        String id = "101";
        String retMsg = restTemplate.postForObject(CommonConstant.CORAL_ADDRESS + "/callservice/testPost2", id, String.class);
        return retMsg;
    }

    @RequestMapping("/put")
    @ResponseBody
    public String put() {
        String id = "101";
        restTemplate.put(CommonConstant.CORAL_ADDRESS + "/callservice/testPut/{kid}", id, id);
        return "更新成功";
    }

    /**
     * restTemplate.delete（）报404错误原因：服务器未返回任何信息，返回值是void,且没有加@ResponseBody.
     * 这回导致客户端无法得知请求是否成功
     *
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete() {
        String id = "101";
        restTemplate.delete(CommonConstant.CORAL_ADDRESS + "/callservice/testDelete/{id}", id);

        return "删除成功";
    }
}
