package com.springboot.cloud.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MallUserRole implements Serializable {
    private Long urId;

    private Long userId;

    private Long roleId;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;

    private static final long serialVersionUID = 1L;


}