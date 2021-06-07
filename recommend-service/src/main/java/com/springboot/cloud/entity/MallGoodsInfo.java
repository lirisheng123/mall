package com.springboot.cloud.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data
public class MallGoodsInfo implements Serializable {
    private Long goodsId;

    private String goodsName;

    private Long goodsCategoryId;

    private String goodsCoverImg;

    private String goodsCarousel;

    private BigDecimal originalPrice;

    private BigDecimal sellingPrice;

    private Integer stockNum;

    private Integer selledStockNum;

    private String tag;

    private Byte goodsSellStatus;

    private Integer createUser;

    private Date createTime;

    private Integer updateUser;

    private Date updateTime;

    private String goodsDetailContent;

    private static final long serialVersionUID = 1L;

}