package com.allinpay.repository.mapper;

import com.allinpay.repository.domain.AllinOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AllinOrderMapper {
    void insert(@Param("allinOrder") AllinOrder allinOrder);

    void insertBatch(@Param("list") List<AllinOrder> list);
}
