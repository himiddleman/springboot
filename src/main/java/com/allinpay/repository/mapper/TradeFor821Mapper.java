package com.allinpay.repository.mapper;

import com.allinpay.repository.domain.TradeFor821;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TradeFor821Mapper {
    int insertBatch(List<TradeFor821> list);

    List<TradeFor821> selectList(@Param("merchantId") String merchantId);
}
