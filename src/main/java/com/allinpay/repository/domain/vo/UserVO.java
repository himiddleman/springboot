package com.allinpay.repository.domain.vo;

import lombok.Data;

import java.util.Set;

@Data
public class UserVO {
    /**
     * admin基础信息
     */
    private String kid;
    private String email;
    private String password;
    private String nickname;
    private String status;
    private String isVerify;
    private String salt;

    /**
     * 角色集合
     */
    private Set<Integer> RoleList;

    /**
     * 权限集合
     */
    private Set<Integer> authorityList;

    /**
     * 资源集合
     */
    private Set<String> urlList;
}
