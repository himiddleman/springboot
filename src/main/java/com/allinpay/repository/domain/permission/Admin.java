package com.allinpay.repository.domain.permission;

import lombok.Data;

import java.util.Date;

@Data
public class Admin {
    private Integer kid;
    private String email;
    private String password;
    private String nickname;
    private String status;
    private String isVerify;
    private Date createTime;
    private Date updateTime;
    private String salt;
}
