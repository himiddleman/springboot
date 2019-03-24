package com.allinpay.service.impl;

import com.allinpay.repository.domain.Admin;
import com.allinpay.repository.mapper.AdminMapper;
import com.allinpay.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin getAdmin(String email, String password) {
        return adminMapper.getAdmin(email, password);
    }
}
