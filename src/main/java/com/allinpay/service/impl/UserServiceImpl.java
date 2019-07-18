package com.allinpay.service.impl;

import com.allinpay.repository.domain.permission.Admin;
import com.allinpay.repository.mapper.AdminMapper;
import com.allinpay.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Admin getAdmin(String email, String password) {
        Admin admin = adminMapper.getAdmin(email, password);
        if (admin != null) {
//            redisTemplate.opsForValue().set("admin", JSON.toJSONString(admin));
        } else {
            log.warn("登录失败！账号或密码错误！");
        }
        return admin;
    }
}
