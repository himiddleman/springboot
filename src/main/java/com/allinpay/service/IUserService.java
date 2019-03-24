package com.allinpay.service;

import com.allinpay.repository.domain.Admin;

public interface IUserService {
    Admin getAdmin(String email, String password);

}
