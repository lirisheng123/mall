package com.springboot.cloud.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 搜索商品的信息
 * Created by macro on 2018/6/19.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Document(indexName = "pms", type = "product",shards = 1,replicas = 0)
public class EsProduct implements Serializable {
    private static final long serialVersionUID = -1L;
    @Id
    private Long goodsId;
//    @Field(type = FieldType.Keyword)
//    private String productSn;
    private Long grandId;

    @Field(type = FieldType.Keyword)
    private String grandName;

    private Long goodsCategoryId;

    @Field(type = FieldType.Keyword)
    private String categoryName;

    private String goodsCoverImg;
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String goodsName;
//    @Field(analyzer = "ik_max_word",type = FieldType.Text)
//    private String subTitle;
//    @Field(analyzer = "ik_max_word",type = FieldType.Text)
//    private String keywords;
    private BigDecimal sellingPrice;
    private Integer selledStockNum;
//    private Integer newStatus;
//    private Integer recommandStatus;
    private Integer stockNum;
    private Byte goodsSellStatus;
//    private Integer promotionType;
//    private Integer sort;
    @Field(type =FieldType.Nested)
    private List<EsProductParamValue> attrValueList;
}
