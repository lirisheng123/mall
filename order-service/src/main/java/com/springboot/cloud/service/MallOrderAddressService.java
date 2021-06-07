package com.springboot.cloud.service;

import com.springboot.cloud.entity.MallOrderAddress;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: lirisheng
 * @Date: 2021/2/23 11:45
 * @Version 1.0
 */
public interface MallOrderAddressService {


    /**
     * 修改订单收货人信息
     */

    int updateOrderAdress(Long id,MallOrderAddress mallOrderAddress);

    /**
     * 增加订单收货人信息
     */
    int insertOrderAdress(MallOrderAddress mallOrderAddress);

    /**
     *  删除订单收货人信息
     */
    int  deleteOrderAdress(Long id);

    /**
     * orderId查询
     * @param orderId
     * @return
     */
    MallOrderAddress  selectByOrderId(Long orderId);
}
