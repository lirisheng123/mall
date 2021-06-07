package com.springboot.cloud.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: lirisheng
 * @Date: 2021/3/24 11:52
 * @Version 1.0
 */
@Document(indexName = "pms", type = "product",shards = 1,replicas = 0)
public class EsProduct implements Serializable {
//    private static final long serialVersionUID = -1L;
//    @Id
//    private Long id;
//    @Field(type = FieldType.Keyword)
//    private String productSn;
//    private Long brandId;
//    @Field(type = FieldType.Keyword)
//    private String brandName;
//    private Long productCategoryId;
//    @Field(type = FieldType.Keyword)
//    private String productCategoryName;
//    private String pic;
//    @Field(analyzer = "ik_max_word",type = FieldType.Text)
//    private String name;
//    @Field(analyzer = "ik_max_word",type = FieldType.Text)
//    private String subTitle;
//    @Field(analyzer = "ik_max_word",type = FieldType.Text)
//    private String keywords;
//    private BigDecimal price;
//    private Integer sale;
//    private Integer newStatus;
//    private Integer recommandStatus;
//    private Integer stock;
//    private Integer promotionType;
//    private Integer sort;
//    @Field(type =FieldType.Nested)
//    private List<EsProductParamValue> attrValueList;

    //省略了所有getter和setter方法
}