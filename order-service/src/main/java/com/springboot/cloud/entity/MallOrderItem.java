package com.springboot.cloud.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class MallOrderItem implements Serializable {
    private Long orderItemId;

    private Long orderId;

    private Long goodsPropertyId;

    private String goodsName;

    private String goodsInfo;

    private String goodsCoverImg;

    private BigDecimal sellingPrice;

    private Integer goodsCount;

    private BigDecimal goodsTotalPrice;

    private Date createTime;

    private static final long serialVersionUID = 1L;


}