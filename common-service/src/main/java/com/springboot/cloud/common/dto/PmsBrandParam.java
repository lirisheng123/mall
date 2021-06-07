package com.springboot.cloud.common.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 品牌传递参数
 * Created by macro on 2018/4/26.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PmsBrandParam {

    @ApiModelProperty(value = "品牌名称",required = true)
    private String name;

    @ApiModelProperty(value = "品牌信息")
    private String firstLetter;

}
