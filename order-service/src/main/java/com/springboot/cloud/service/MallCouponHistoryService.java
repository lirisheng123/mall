package com.springboot.cloud.service;

import com.springboot.cloud.dto.CouponHistoryParam;
import com.springboot.cloud.dto.MallCouponParam;
import com.springboot.cloud.entity.MallCoupon;
import com.springboot.cloud.entity.MallCouponHistory;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/23 11:42
 * @Version 1.0
 */
public interface MallCouponHistoryService {

    /**
     * 会员添加优惠券
     */

    void add(Long userId,Long couponId,String memberNickname);

    /**
     * 获取优惠券历史列表
     */
    List<CouponHistoryParam> listHistory(Long userId, Integer useStatus, Integer pagesize, Integer pageNum);

    /**
     * 根据购物车信息获取可用优惠券
     */
//    List<MallCouponHistoryDetail> listCart(List<CartPromotionItem> cartItemList, Integer type);

    /**
     * 获取当前商品相关优惠券
     */
//    List<SmsCoupon> listByProduct(Long productId);

    /**
     * 获取用户优惠券列表
     */
    List<MallCoupon> list(Long userId,Integer useStatus);


    /**
     *根据userId和金额来获取用户可用的优惠卷
     * filter条件  日期 userId count
     */
    List<CouponHistoryParam>  getAvaliableCouptonByUserIdAndCount(Long userId, String count,
                                                                  Integer pageSize,Integer pageNum);

    /**
     * 分页查询优惠券领取记录
     * @param couponId 优惠券id
     * @param useStatus 使用状态
     * @param orderSn 使用订单号码
     */
//    List<MallCouponHistory> list(Long couponId, Integer useStatus, String orderSn, Integer pageSize, Integer pageNum);


    /**
     * 根据id获取历史记录
     */
    MallCouponHistory select(Long couponHistoryId);

    /**
     * 根据id更改
     */
    int update(Long couponHistoryId,MallCouponHistory mallCouponHistory);
}
