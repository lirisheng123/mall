package com.springboot.cloud.dto;

import lombok.Data;

/**
 * @Author: lirisheng
 * @Date: 2021/3/20 21:02
 * @Version 1.0
 */
@Data
public class ProductProperty {
     Long categoryPropertyId;
    String  goodsPropertyValue;
    Double  originalPricePro;
    Integer selledStockNumPro;
    Double  sellingPricePro;
    Integer  stockNumPro;

}
