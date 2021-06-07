package com.springboot.cloud.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class MallGoodsPropertyRelate implements Serializable {
    private Long goodsPropertyRelateId;

    private Long goodsId;

    private Long categoryPropertyId;

    private String goodsPropertyRelateName;

    private String goodsPropertyRelateValue;

    private static final long serialVersionUID = 1L;


}