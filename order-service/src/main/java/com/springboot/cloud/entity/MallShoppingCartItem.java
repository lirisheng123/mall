package com.springboot.cloud.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class MallShoppingCartItem implements Serializable {
    private Long cartItemId;

    private Long userId;

    private Long goodsPropertyId;

    private String goodsName;

    private String goodsInfo;

    private String goodsCoverImg;

    private Integer goodsCount;

    private BigDecimal goodsPrice;

    private Byte isDeleted;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

}