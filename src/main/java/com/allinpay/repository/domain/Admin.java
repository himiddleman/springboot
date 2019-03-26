package com.allinpay.repository.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;
import java.io.Serializable;

@Setter
@Getter
@ToString
public class Admin implements Serializable {
    @Id
    private String kid;
    private String email;
    private String password;
    private String nickname;
    private String status;
    private String isVerify;
}
