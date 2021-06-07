package com.springboot.cloud.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MallGoodsParam implements Serializable {
    private Long goodsParamId;

    private Long goodsId;

    private String goodsParamName;

    private String goodsParamValue;

    private Integer createUser;

    private Date createTime;

    private Integer updateUser;

    private Date updateTime;

    private static final long serialVersionUID = 1L;


}