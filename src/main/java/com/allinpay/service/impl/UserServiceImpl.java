package com.allinpay.service.impl;

import com.alibaba.fastjson.JSON;
import com.allinpay.repository.domain.Admin;
import com.allinpay.repository.mapper.AdminMapper;
import com.allinpay.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Admin getAdmin(String email, String password) {
        Admin admin = adminMapper.getAdmin(email, password);
        if (admin != null) {
            redisTemplate.opsForValue().set("admin", JSON.toJSONString(admin));
        }
        return admin;
    }
}
