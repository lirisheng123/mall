package com.springboot.cloud.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author: lirisheng
 * @Date: 2021/3/31 21:43
 * @Version 1.0
 */
@Data
public class FlashPromotionParams {

    private String name;

    private Date nowTime;

    private  Date endTime;

    private Integer status;
}
