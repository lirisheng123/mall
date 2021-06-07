package com.springboot.cloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 产品查询参数
 * Created by macro on 2018/4/27.
 */
@Data
public class MallGoodsInfoQueryParam {

    @ApiModelProperty("上架状态")
    private Integer goodsSellStatus;
    @ApiModelProperty("商品名称模糊关键字")
    private String goodsName;
    @ApiModelProperty("商品货号")
    private String goodsId;
    @ApiModelProperty("商品分类编号")
    private Long goodsCategoryId;
//    @ApiModelProperty("品牌编号")
//    private Long grandId;
//    @ApiModelProperty("商品品牌编号")
//    private Long grandId;
}
