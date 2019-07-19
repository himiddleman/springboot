package com.allinpay.service.impl;

import com.allinpay.repository.domain.permission.Admin;
import com.allinpay.repository.mapper.AdminMapper;
import com.allinpay.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public void addUser(Admin admin) {
        adminMapper.insert(admin);
        addUser2(admin);
        int i = 1 / 0;
    }

    @Override
    @Transactional
    public void addUser2(Admin admin) {
        admin.setKid(101);
        adminMapper.insert(admin);
    }
}
