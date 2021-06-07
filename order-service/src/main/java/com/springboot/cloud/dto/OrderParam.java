package com.springboot.cloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 生成订单时传入的参数
 * Created by macro on 2018/8/30.
 */
@Data
public class OrderParam {
   private Long userId;
   private Integer status;
   private String orderNo;
   private Date date;
   private String userName;
}
