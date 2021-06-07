package com.springboot.cloud.service.impl;

import com.springboot.cloud.dao.MallDeliveryLogisticsMapper;
import com.springboot.cloud.entity.MallDeliveryLogistics;
import com.springboot.cloud.entity.MallOrder;
import com.springboot.cloud.service.MallDeliveryLogisticsService;
import com.springboot.cloud.service.MallOrderService;
import com.springboot.cloud.util.OrderUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: lirisheng
 * @Date: 2021/2/23 11:43
 * @Version 1.0
 */
@Service
public class MallDeliveryLogisticsServiceImpl implements MallDeliveryLogisticsService {

    @Autowired
    MallDeliveryLogisticsMapper mallDeliveryLogisticsMapper;
    @Autowired
    MallOrderService mallOrderService;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(MallDeliveryLogistics mallDeliveryLogistics) {
        String deliverySn = OrderUtils.getOrderCode(mallDeliveryLogistics.getOrderId().intValue());
        mallDeliveryLogistics.setDeliveryLogisticsNo(deliverySn);

        //更改订单的状态为待收货状态
        mallOrderService.updateOrderStatus(mallDeliveryLogistics.getOrderId(),2,new Date());
        return mallDeliveryLogisticsMapper.insert(mallDeliveryLogistics);
    }
    @Override
    public MallDeliveryLogistics selectByOrderId(Long orderId) {
        return mallDeliveryLogisticsMapper.selectByOrderId(orderId);
    }
}
