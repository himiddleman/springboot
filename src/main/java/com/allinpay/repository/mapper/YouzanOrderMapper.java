package com.allinpay.repository.mapper;

import com.allinpay.repository.domain.YouzanOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * author: tanguang
 * data: 2020-04-08
 **/
public interface YouzanOrderMapper {
    int saveBatch(@Param("list") List<YouzanOrder> orderList);
}
