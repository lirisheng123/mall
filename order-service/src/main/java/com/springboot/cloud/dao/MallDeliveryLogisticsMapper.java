package com.springboot.cloud.dao;


import com.springboot.cloud.entity.MallDeliveryLogistics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;
@Mapper
public interface MallDeliveryLogisticsMapper {
    int deleteByPrimaryKey(Long deliveryLogisticsId);

    int insert(MallDeliveryLogistics record);

    MallDeliveryLogistics selectByPrimaryKey(Long deliveryLogisticsId);

    List<MallDeliveryLogistics> selectAll();

    int updateByPrimaryKey(MallDeliveryLogistics record);

    @Select("SELECT  * FROM  mall_delivery_logistics WHERE  order_id=#{orderId} ")
    MallDeliveryLogistics selectByOrderId(Long orderId);
}