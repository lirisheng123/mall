package com.springboot.cloud.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MallCouponHistory implements Serializable {
    private Long couponHistoryId;

    private Long couponId;

    private Long userId;

    private Long orderId;

    private String memberNickname;

    private Date createTime;

    private Integer useStatus;

    private Date useTime;

    private String orderSn;

    private static final long serialVersionUID = 1L;


}