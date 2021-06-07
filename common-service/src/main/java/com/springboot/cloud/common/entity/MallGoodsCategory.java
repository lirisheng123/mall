package com.springboot.cloud.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MallGoodsCategory implements Serializable {
    private Long categoryId;

    private Byte categoryLevel;

    private Long parentId;

    private String categoryName;

    private Integer categoryRank;

    private Byte isDeleted;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;

    private static final long serialVersionUID = 1L;


}