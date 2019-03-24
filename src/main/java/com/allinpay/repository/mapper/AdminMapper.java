package com.allinpay.repository.mapper;

import com.allinpay.repository.domain.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {

    Admin getAdmin(@Param("email") String email,
                   @Param("password") String password);
}
