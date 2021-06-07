package com.springboot.cloud.dao;


import com.springboot.cloud.entity.MallOrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface MallOrderItemMapper {
    int deleteByPrimaryKey(Long orderItemId);

    int insert(MallOrderItem record);

    MallOrderItem selectByPrimaryKey(Long orderItemId);

    List<MallOrderItem> selectAll();

    int updateByPrimaryKey(MallOrderItem record);

    @Select("SELECT * FROM  mall_order_item WHERE  order_id =#{orderId} ")
    List<MallOrderItem> selectByOrderId(Long orderId);

    int insertList(List<MallOrderItem> mallOrderItems);

    @Select("SELECT  * FROM  mall_order_item JOIN mall_order ON (mall_order_item.order_id=mall_order.order_id)" +
            "WHERE  mall_order.order_status=#{status} ")
    List<MallOrderItem> selectByStatus(Integer status);

}