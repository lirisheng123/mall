package com.springboot.cloud.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class MallHomeAdvertise implements Serializable {
    private Long mallAdvertiseId;

    private String name;

    private String pic;

    private Date startTime;

    private Date endTime;

    private Integer status;

    private Integer clickCount;

    private String url;

    private String note;

    private Integer sort;

    private static final long serialVersionUID = 1L;


}