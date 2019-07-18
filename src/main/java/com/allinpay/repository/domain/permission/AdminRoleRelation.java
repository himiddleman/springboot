package com.allinpay.repository.domain.permission;

import lombok.Data;

@Data
public class AdminRoleRelation {
    private Integer kid;
    private Integer adminId;
    private Integer roleId;
}
