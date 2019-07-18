package com.allinpay.repository.domain.permission;

import lombok.Data;

@Data
public class Admin {
    private Integer kid;
    private String email;
    private String password;
    private String nickname;
    private String status;
    private String isVerify;
    private java.sql.Timestamp createTime;
    private java.sql.Timestamp updateTime;
    private String salt;
}
