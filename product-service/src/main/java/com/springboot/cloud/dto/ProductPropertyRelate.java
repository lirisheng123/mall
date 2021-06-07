package com.springboot.cloud.dto;

import lombok.Data;

/**
 * @Author: lirisheng
 * @Date: 2021/3/21 17:31
 * @Version 1.0
 */
@Data
public class ProductPropertyRelate {

    private Long categoryPropertyId;

    private String goodsPropertyRelateName;

    private String goodsPropertyRelateValue;
}
