package com.allinpay.repository.mapper;

import com.allinpay.repository.domain.permission.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {

    Admin getAdmin(@Param("email") String email,
                   @Param("password") String password);

    Admin selectByUsername(String username);

    int insert(@Param("admin") Admin admin);
}
