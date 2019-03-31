package com.allinpay.repository.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@NameStyle(Style.camelhumpAndUppercase)
public class AllinOrder {
    private String orderNo;
    private Date createTime;
    private String proDesc;
    private BigDecimal price;
    private Integer count;
    private String buyer;
    private BigDecimal amount;
}
