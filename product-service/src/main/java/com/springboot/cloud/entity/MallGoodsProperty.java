package com.springboot.cloud.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class MallGoodsProperty implements Serializable {
    private Long goodsPropertyId;

    private Long goodsId;

    private String goodsPropertyName;

    private String goodsPropertyValue;

    private Integer createUser;

    private Date createTime;

    private Integer updateUser;

    private Date updateTime;

    private BigDecimal originalPrice;

    private BigDecimal sellingPrice;

    private Integer stockNum;

    private Integer selledStockNum;

    private static final long serialVersionUID = 1L;


}