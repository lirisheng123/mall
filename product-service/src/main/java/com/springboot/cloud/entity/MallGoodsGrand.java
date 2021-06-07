package com.springboot.cloud.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MallGoodsGrand implements Serializable {
    private Long goodsGrandId;

    private Long goodsId;

    private Long grandId;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;

    private static final long serialVersionUID = 1L;


}