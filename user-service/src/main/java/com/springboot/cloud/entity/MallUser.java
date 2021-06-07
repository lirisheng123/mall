package com.springboot.cloud.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MallUser implements Serializable {
    private Long userId;

    private String loginName;

    private String passwordMd5;

    private String phoneNumber;

    private Byte isDeleted;

    private Byte lockedFlag;

    private Date createTime;

    private static final long serialVersionUID = 1L;


}