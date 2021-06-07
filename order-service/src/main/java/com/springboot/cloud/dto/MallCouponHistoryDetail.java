package com.springboot.cloud.dto;


import com.springboot.cloud.entity.MallCoupon;
import com.springboot.cloud.entity.MallCouponHistory;

import lombok.Data;

/**
 * 优惠券领取历史详情封装
 * Created by macro on 2018/8/29.
 */
@Data
public class MallCouponHistoryDetail extends MallCouponHistory {
    //相关优惠券信息
    private MallCoupon coupon;

}
