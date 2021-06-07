package com.springboot.cloud.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MallCategoryParam implements Serializable {
    private Long categoryParamId;

    private Long categoryId;

    private String categoryParamName;

    private String categoryParamValue;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;

    private static final long serialVersionUID = 1L;


}