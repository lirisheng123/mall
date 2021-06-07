package com.springboot.cloud.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MallOrderOperateHistory implements Serializable {
    private Long id;

    private Long orderId;

    private String operateMan;

    private Date createTime;

    private Integer orderStatus;

    private String note;

    private static final long serialVersionUID = 1L;


}