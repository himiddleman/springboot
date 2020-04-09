package com.allinpay.service;

import com.allinpay.repository.domain.YouzanOrder;

import java.util.List;

public interface YouzanOrderService {
    int saveBatch(List<YouzanOrder> orderList);
}
