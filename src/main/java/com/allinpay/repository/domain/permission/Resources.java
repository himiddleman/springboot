package com.allinpay.repository.domain.permission;

import lombok.Data;

@Data
public class Resources {
    private Integer kid;
    private String name;
    private String description;
    private String url;
    private String status;
}
