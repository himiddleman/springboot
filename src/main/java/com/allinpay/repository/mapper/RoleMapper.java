package com.allinpay.repository.mapper;

import com.allinpay.repository.domain.permission.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    List<Role> selectRoleList(@Param("kid") Integer kid);
}
