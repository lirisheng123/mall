package com.springboot.cloud.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MallRecommend implements Serializable {
    private Long id;

    private Long userId;

    private Long goodId;

    private Integer preference;

    private static final long serialVersionUID = 1L;


}