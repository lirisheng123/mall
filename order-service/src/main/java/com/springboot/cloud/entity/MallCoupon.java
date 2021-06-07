package com.springboot.cloud.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class MallCoupon implements Serializable {
    private Long mallCouponId;

    private String name;

    private Integer count;

    private BigDecimal amount;

    private Integer perLimit;

    private BigDecimal minPoint;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date startTime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date endTime;

    private String note;

    private Integer receiveCount;

    private Integer useCount;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date enableTime;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;


}