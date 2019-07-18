package com.allinpay.repository.domain.permission;

import lombok.Data;

@Data
public class AuthorityResourcesRelation {
    private Integer kid;
    private Integer authorityId;
    private Integer resourcesId;
}
