package com.springboot.cloud.dao;


import com.springboot.cloud.entity.MallOrderAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface MallOrderAddressMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(MallOrderAddress record);

    MallOrderAddress selectByPrimaryKey(Long orderId);

    List<MallOrderAddress> selectAll();

    int updateByPrimaryKey(MallOrderAddress record);

    @Select("SELECT  * FROM  mall_order_address WHERE order_id=#{orderId} ")
    MallOrderAddress selectByOrderId(Long orderId);
}