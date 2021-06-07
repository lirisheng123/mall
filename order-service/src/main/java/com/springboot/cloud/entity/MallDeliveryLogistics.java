package com.springboot.cloud.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MallDeliveryLogistics implements Serializable {
    private Long deliveryLogisticsId;

    private Long orderId;

    private String deliveryLogisticsNo;

    private String deliveryCompanyName;

    private Long companyAddressId;

    private Long orderAddressId;

    private String operationName;

    private Date createTime;

    private static final long serialVersionUID = 1L;

 }