package com.springboot.cloud.dao;


import com.springboot.cloud.dto.MallCouponParam;
import com.springboot.cloud.entity.MallCoupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper
public interface MallCouponMapper {
    int deleteByPrimaryKey(Long mallCouponId);

    int insert(MallCoupon record);

    MallCoupon selectByPrimaryKey(Long mallCouponId);

    List<MallCoupon> selectAll();

    int updateByPrimaryKey(MallCoupon record);

    List<MallCoupon> selectByMulParam(MallCouponParam mallCouponParam);

    @Update("UPDATE mall_coupon SET count=#{count} ,receive_count=#{receiveCount} " +
            "WHERE  mall_coupon_id=#{mallCouponId} ")
    int updateCountAndReciveCountByPrimaryKey(MallCoupon mallCoupon);

    @Update("UPDATE mall_coupon SET use_count=use_count+1 " +
            "WHERE  mall_coupon_id=#{id} ")
    int updateUserCount(@Param("id") Long id);
}