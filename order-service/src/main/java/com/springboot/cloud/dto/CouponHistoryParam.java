package com.springboot.cloud.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.cloud.entity.MallCouponHistory;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: lirisheng
 * @Date: 2021/3/27 16:00
 * @Version 1.0
 */
@Data
public class CouponHistoryParam extends MallCouponHistory {

    private String name;

    private Integer count;

    private BigDecimal amount;

    private BigDecimal minPoint;

    private Integer perLimit;

    private Integer receiveCount;

    private Integer useCount;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date startTime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date endTime;

    private String note;
}
