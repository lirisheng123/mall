package com.springboot.cloud.common.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class MallGrand implements Serializable {

    @ApiModelProperty("品牌分类ID")
    private Long grandId;


    @ApiModelProperty("品牌名")
    private String grandName;

    private String grandInfo;


    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime;

    @ApiModelProperty("创建者")
    private Integer createUser;


    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updateTime;


    @ApiModelProperty("修改者")
    private Integer updateUser;

    private static final long serialVersionUID = 1L;

}