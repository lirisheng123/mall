package com.springboot.cloud.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class MallRole implements Serializable {
    private Long roleId;

    private String roleName;

    private String roleInfo;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;

    private static final long serialVersionUID = 1L;


}