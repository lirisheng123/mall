package com.springboot.cloud.common.entity.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MallUserAddress implements Serializable {
    private Long addressId;

    private Long userId;

    private String userName;

    private String userPhone;

    private Byte defaultFlag;

    private String provinceName;

    private String cityName;

    private String regionName;

    private String detailAddress;

    private Byte isDeleted;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;


}