package com.springboot.cloud.common.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: lirisheng
 * @Date: 2021/2/20 17:30
 * @Version 1.0
 */
@Data
public class MallGrandQueryParam {

    @ApiModelProperty("品牌分类ID")
    private Long grandId;


    @ApiModelProperty("品牌名")
    private String grandName;

    @ApiModelProperty("品牌详情")
    private String grandInfo;
}
