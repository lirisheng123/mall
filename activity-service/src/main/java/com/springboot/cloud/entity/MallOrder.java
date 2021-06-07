package com.springboot.cloud.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class MallOrder implements Serializable {
    private Long orderId;

    private String orderNo;

    private Long userId;

    private BigDecimal totalAmount;

    private Byte payStatus;

    private Byte payType;

    private Date payTime;

    private Byte orderStatus;

    private String extraInfo;

    private Byte isDeleted;

    private Date createTime;

    private Date updateTime;

    private BigDecimal payAmount;

    private BigDecimal freightAmount;

    private BigDecimal couponAmount;

    private Date deliveryTime;

    private Date receiveTime;

    private static final long serialVersionUID = 1L;


}