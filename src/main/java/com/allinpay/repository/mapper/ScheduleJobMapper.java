package com.allinpay.repository.mapper;

import com.allinpay.repository.domain.ScheduleJob;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScheduleJobMapper {
    ScheduleJob selectOne(@Param("kid") Integer kid);

    List<ScheduleJob> selectList();

    int save(@Param("job") ScheduleJob job);
}
