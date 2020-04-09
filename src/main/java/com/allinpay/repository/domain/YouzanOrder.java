package com.allinpay.repository.domain;

import lombok.Data;

/**
 * author: tanguang
 * data: 2020-04-08
 **/
@Data
public class YouzanOrder {
    private String orderNo;
    private String currency;
    private String price;
    private String buyerName;
    private String buyerIdNo;
    private String goodsType;
    private String goodsName;
    private String goodsCount;
    private String payTime;
    private String expressType;
    private String expressNo;
    private String freight;
    private String tax;
    private String other_fee;
}
