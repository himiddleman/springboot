package com.allinpay.service.impl;

import com.allinpay.repository.domain.YouzanOrder;
import com.allinpay.repository.mapper.YouzanOrderMapper;
import com.allinpay.service.YouzanOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author: tanguang
 * data: 2020-04-08
 **/
@Service
public class YouzanOrderServiceImpl implements YouzanOrderService {
    @Autowired
    private YouzanOrderMapper youzanOrderMapper;

    @Override
    public int saveBatch(List<YouzanOrder> orderList) {
        return youzanOrderMapper.saveBatch(orderList);
    }
}
