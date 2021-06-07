package com.springboot.cloud.service;

import com.springboot.cloud.entity.MallOrder;
import com.springboot.cloud.entity.MallOrderItem;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/23 11:45
 * @Version 1.0
 */
public interface MallOrderItemService {

    /**
     * 根据订单ID获取订单详情
     */
    List<MallOrderItem> selectByOrderId(Long orderId);


    /**
     * 获取指定订单详情
     */
    MallOrderItem select(Long id);

    /**
     * 批量增加订单详情
     */
     int insertList(List<MallOrderItem> mallOrderItems);


    /**
     * 批量删除订单详情
     */
     int deleteList(List<Long> id);


}
