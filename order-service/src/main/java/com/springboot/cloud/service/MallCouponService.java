package com.springboot.cloud.service;

import com.springboot.cloud.dto.MallCouponParam;
import com.springboot.cloud.entity.MallCoupon;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/23 11:41
 * @Version 1.0
 */
public interface MallCouponService {

    /**
     * 添加优惠券
     */
    @Transactional
    int create(MallCoupon mallCoupon);

    /**
     * 根据优惠券id删除优惠券
     */
    @Transactional
    int delete(Long id);

    /**
     * 根据优惠券id更新优惠券信息
     */
    @Transactional
    int update(Long id, MallCoupon mallCoupon);

    /**
     * 分页获取优惠券列表
     */
    List<MallCoupon> list(MallCouponParam mallCouponParam, Integer pageSize, Integer pageNum);

    /**
     * 获取优惠券详情
     * @param id 优惠券表id
     */
    MallCoupon getItem(Long id);

    /**
     * 根据Id修改总数和领取的数量
     */
    int updateCountAndReciveCountByPrimaryKey(MallCoupon mallCoupon);

    /**
     * 根据id修改使用的数量
     */
    int updateUserCount(Long id);

}
