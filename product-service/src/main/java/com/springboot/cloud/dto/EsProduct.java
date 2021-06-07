package com.springboot.cloud.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 搜索商品的信息
 * Created by macro on 2018/6/19.
 */
@Data
public class EsProduct implements Serializable {
    private static final long serialVersionUID = -1L;

    private Long goodsId;

    private Long grandId;

    private String grandName;
    private Long goodsCategoryId;

    private String categoryName;
    private String goodsCoverImg;

    private String goodsName;

    private BigDecimal sellingPrice;
    private Integer selledStockNum;

    private Integer stockNum;

    private Byte goodsSellStatus;

    private List<EsProductParamValue> attrValueList;
}
