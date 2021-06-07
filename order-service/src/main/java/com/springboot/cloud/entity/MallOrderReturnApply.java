package com.springboot.cloud.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class MallOrderReturnApply implements Serializable {
    private Long orderReturnApplyId;

    private Long orderId;

    private Long companyAddressId;

    private Date createTime;

    private BigDecimal returnAmount;

    private String returnName;

    private String returnPhone;

    private Integer status;

    private Date handleTime;

    private String reason;

    private String handleNote;

    private String handleMan;

    private String receiveMan;

    private Date receiveTime;

    private String receiveNote;

    private static final long serialVersionUID = 1L;


}