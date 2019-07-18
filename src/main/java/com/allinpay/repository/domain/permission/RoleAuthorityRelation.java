package com.allinpay.repository.domain.permission;

import lombok.Data;

@Data
public class RoleAuthorityRelation {
    private Integer kid;
    private String roleId;
    private String authorityId;
}
