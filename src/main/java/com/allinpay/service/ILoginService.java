package com.allinpay.service;

import com.allinpay.repository.domain.permission.Admin;
import com.allinpay.repository.domain.vo.UserVO;

public interface ILoginService {
    UserVO generateAuthority(Admin admin);
}
