package com.allinpay.repository.mapper;

import com.allinpay.repository.domain.permission.Resources;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface ResourcesMapper {
    List<Resources> selectResourceList(@Param("authoritySet") Set<Integer> authoritySet);
}
