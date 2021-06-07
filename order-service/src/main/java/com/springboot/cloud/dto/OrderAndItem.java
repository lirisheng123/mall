package com.springboot.cloud.dto;

import com.springboot.cloud.entity.MallOrder;
import com.springboot.cloud.entity.MallOrderItem;
import lombok.Data;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/4/2 15:57
 * @Version 1.0
 */
@Data
public class OrderAndItem extends MallOrder {

    List<MallOrderItem> mallOrderItems;
}
