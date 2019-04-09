package com.allinpay.repository.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 821流水表
 */
@Getter
@Setter
@ToString
@Table(name = "temp_t_trade_list_821")
@NameStyle(Style.camelhumpAndUppercase)
public class TradeFor821 {
    /**
     * 分公司代码
     */
    private String subCompanyCode;
    /**
     * 收单机构
     */
    private String acquirerCode;
    /**
     * 32域机构代码
     */
    private String code32;
    /**
     * 发送机构代码
     */
    private String sendOrgCode;
    /**
     * 商户编号
     */
    private String mchtId;
    /**
     * 商户名称
     */
    private String mchtName;
    /**
     * 商户营业名称
     */
    @Column(name = "BUSINESSNAME")
    private String businessName;
    /**
     * mcc18
     */
    private String mcc18;
    /**
     * mcc42
     */
    private String mcc42;
    /**
     * mcc大类
     */
    private String mcc;
    /**
     * 交易日期
     */
    private String tradeTime;
    /**
     * 清算日期
     */
    private String settDate;
    /**
     * 交易分类
     */
    private String tradeClass;
    /**
     * 交易类型
     */
    private String tradeType;
    /**
     * 交易状态
     */
    private String tradeStatus;
    /**
     * 清算标志
     */
    private String settStatus;
    /**
     * 交易金额
     */
    private String amount;
    /**
     * 交易卡号
     */
    private String cardNo;
    /**
     * 卡类别
     */
    private String cardType;
    /**
     * 发卡机构代码
     */
    private String issuingCode;
    /**
     * 发卡机构名称
     */
    private String issuingBank;
    /**
     * 分店代码
     */
    private String branchId;
    /**
     * 分店名称
     */
    private String branchName;
    /**
     * 终端编码
     */
    private String termId;
    /**
     * 商户手续费
     */
    private String fee;
    /**
     * 商户产品手续费
     */
    private String productFee;
    /**
     * 商户结算金额
     */
    private String settAmt;
    /**
     * 集团商户编码
     */
    private String groupCode;
    /**
     * 银联卡境内外标志
     */
    private String innerForeinFlag;
    /**
     * 特殊计费类型
     */
    private String specialFeeType;
    /**
     * 特殊计费档次
     */
    private String specialFeeLevel;
    /**
     * 输入点方式
     */
    private String touchModel;
    /**
     * 系统跟踪号
     */
    private String trackCode;
    /**
     * 通联收益
     */
    private String yield;
    /**
     * 通联应付品牌服务费
     */
    private String serviceFee;
    /**
     * 支付方式
     */
    private String tradeChannel;
    /**
     * 检索参考号
     */
    private String referNo;
    /**
     * 保留关键字1
     */
    private String keyWord1;
    /**
     * 保留关键字2
     */
    private String keyWord2;
    /**
     * 保留关键字3
     */
    private String keyWord3;
    /**
     * 保留关键字4
     */
    private String keyWord4;
    /**
     * 保留关键字5
     */
    private String keyWord5;
}
