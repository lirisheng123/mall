package com.springboot.cloud.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class MallFlashPromotionSession implements Serializable {
    private Long mallFlashPromotionId;

    private String name;

    private Date startTime;

    private Date endTime;

    private Integer status;

    private Date createTime;

    private Long goodsId;

    private BigDecimal sellingPrice;

    private BigDecimal price;

    private Integer count;

    private Integer selledCount;

    private Integer sort;

    private static final long serialVersionUID = 1L;



}