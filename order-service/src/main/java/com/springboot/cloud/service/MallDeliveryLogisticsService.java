package com.springboot.cloud.service;

import com.springboot.cloud.entity.MallDeliveryLogistics;
import org.springframework.stereotype.Service;

/**
 * @Author: lirisheng
 * @Date: 2021/2/23 11:43
 * @Version 1.0
 */


public interface MallDeliveryLogisticsService {

    /**
     * 填写物流单号
     */
     int  insert(MallDeliveryLogistics mallDeliveryLogistics);

    /**
     * 获取物流单号
     */
     MallDeliveryLogistics selectByOrderId(Long orderId);
}
