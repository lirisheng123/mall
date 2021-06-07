package com.springboot.cloud.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MallCategoryGrand implements Serializable {
    private Long categoryGrandId;

    private Long categoryId;

    private Long grandId;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;

    private static final long serialVersionUID = 1L;

}