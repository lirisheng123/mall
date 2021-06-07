package com.springboot.cloud.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class MallCategoryProperty implements Serializable {
    private Long categoryPropertyId;

    private Long categoryId;

    private String categoryParamName;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;


}