package com.springboot.cloud.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * 搜索商品的属性信息
 * Created by macro on 2018/6/27.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EsProductParamValue implements Serializable {
    private static final long serialVersionUID = 1L;
//    private Long id;
    private Long goodsParamId;
    //属性值
    @Field(type = FieldType.Keyword)
    private String goodsParamValue;
    //属性参数：0->规格；1->参数
//    private Integer type;
    //属性名称
    @Field(type=FieldType.Keyword)
    private String goodsParamName;
}
