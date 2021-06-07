package com.springboot.cloud.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author: lirisheng
 * @Date: 2021/3/30 1:05
 * @Version 1.0
 */
@Data
public class AdvertiesParams {

    private String name;
    private Date endTime;
}
