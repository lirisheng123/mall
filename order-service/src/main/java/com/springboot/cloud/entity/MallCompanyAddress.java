package com.springboot.cloud.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class MallCompanyAddress implements Serializable {
    private Long id;

    private String addressName;

    private Integer sendStatus;

    private Integer receiveStatus;

    private String name;

    private String phone;

    private String province;

    private String city;

    private String region;

    private String detailAddress;

    private static final long serialVersionUID = 1L;


}