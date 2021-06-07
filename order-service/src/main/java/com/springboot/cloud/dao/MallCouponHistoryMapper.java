package com.springboot.cloud.dao;


import com.springboot.cloud.entity.MallCoupon;
import com.springboot.cloud.entity.MallCouponHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper
public interface MallCouponHistoryMapper {
    int deleteByPrimaryKey(Long couponHistoryId);

    int insert(MallCouponHistory record);

    MallCouponHistory selectByPrimaryKey(Long couponHistoryId);

    List<MallCouponHistory> selectAll();

    int updateByPrimaryKey(MallCouponHistory record);

    @Select("SELECT count(*) FROM  mall_coupon_history where  user_id=#{userId} AND  coupon_id=#{couponId} ")
    Long countCouponByUserId(@Param("userId")Long userId,@Param("couponId")Long couponId);

    @Select("SELECT * FROM  mall_coupon_history WHERE  user_id=#{userId} " +
            "AND  use_status=#{useStatus} ")
    List<MallCouponHistory> selectUseStatusByPrimaryId(@Param("userId") Long userId,@Param("useStatus")Integer useStatus);

    /**
     * 获取指定会员优惠券列表
     */
    List<MallCoupon> getCouponList(@Param("userId") Long userId, @Param("useStatus")Integer useStatus);

//    @Select("SELECT  * FROM  mall_coupon_history WHERE  user_id=#{userId} AND c")
//    List<MallCouponHistory>  getAvaliableCoupton(@Param("userId") Long userId,
//                                                 @Param("count")Double count,
//                                                 @Param("startTime")Double startTime);

//    @Update("UPDATE  mall_coupon_history SET  use_status=2 WHERE  user_id=#{userId} " +
//            "AND  ")
   int updateExceedEndTimeStatus(Long userId);
}