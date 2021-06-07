package com.springboot.cloud.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 搜索商品的品牌名称，分类名称及属性
 * Created by macro on 2018/6/27.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EsProductRelatedInfo {

    private List<ProductGrand> productGrands;
    private List<ProductCategory> productCategorys;
    private List<ProductParam> productParams;

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class ProductParam {

        private String goodsParamName;
        private List<String> goodsParamValue;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class ProductGrand {
        private Long grandId;
        private String grandName;

    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class ProductCategory {
        private Long goodsCategoryId;
        private String categoryName;

    }


}
