package com.allinpay.repository.mapper;

import com.allinpay.repository.domain.permission.Authority;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface AuthorityMapper {
    List<Authority> selectAuthorityList(@Param("roleSet") Set<Integer> roleSet);
}
