package com.springboot.cloud.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
public class MallSecondProperty implements Serializable {
    private Long mallSecondPropertyId;

    private Long mallFlashPromotionId;

    private Long goodsPropertyId;

    private String propertyName;

    private BigDecimal propertySellingPrice;

    private BigDecimal propertyPrice;

    private Integer propertyCount;

    private Integer propertySelledCount;

    private Integer limit;

    private static final long serialVersionUID = 1L;


}