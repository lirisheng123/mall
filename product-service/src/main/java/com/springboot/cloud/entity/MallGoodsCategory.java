package com.springboot.cloud.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MallGoodsCategory implements Serializable {
    private Long categoryId;

    private Byte categoryLevel;

    private Long parentId;

    private String categoryName;

    private String categoryPic;

    private Integer categoryRank;

    private Byte isDeleted;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;

    private static final long serialVersionUID = 1L;


}