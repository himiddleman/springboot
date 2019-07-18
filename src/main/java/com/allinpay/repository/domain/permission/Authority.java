package com.allinpay.repository.domain.permission;

import lombok.Data;

@Data
public class Authority {
    private Integer kid;
    private String name;
    private String description;
    private String status;
}
