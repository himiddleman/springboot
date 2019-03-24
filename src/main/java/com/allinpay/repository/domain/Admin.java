package com.allinpay.repository.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;

@Setter
@Getter
@ToString
public class Admin {
    @Id
    private String kid;
    private String email;
    private String password;
    private String nickname;
    private String status;
    private String isVerify;
}
