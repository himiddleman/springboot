package com.allinpay.service;

import com.allinpay.repository.domain.permission.Admin;

public interface IUserService {
    Admin getAdmin(String email, String password);

    void addUser(Admin admin);

    void addUser2(Admin admin);
}
