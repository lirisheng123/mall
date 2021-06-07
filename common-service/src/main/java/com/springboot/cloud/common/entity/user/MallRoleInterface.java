package com.springboot.cloud.common.entity.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MallRoleInterface implements Serializable {
    private Long uiId;

    private Long roleId;

    private Long interId;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;

    private static final long serialVersionUID = 1L;


}