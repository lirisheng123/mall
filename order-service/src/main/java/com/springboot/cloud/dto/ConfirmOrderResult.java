package com.springboot.cloud.dto;


import com.springboot.cloud.entity.MallCouponHistory;
import com.springboot.cloud.entity.MallShoppingCartItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 确认单信息封装
 * Created by macro on 2018/8/30.
 */
@Data
public class ConfirmOrderResult {
    //包含优惠信息的购物车信息
    private List<MallShoppingCartItem> mallShoppingCartItems;


    //用户收货地址列表
    //用户的收获地址在App层再进行分装
//    private List<UmsMemberReceiveAddress> memberReceiveAddressList;
    //用户可用优惠券列表
    private List<MallCouponHistory> couponHistoryDetailList;
    //计算的金额
    private CalcAmount calcAmount;


    public static class CalcAmount{
        //订单商品总金额
        private BigDecimal totalAmount;
        //运费
        private BigDecimal freightAmount;
        //活动优惠
        private BigDecimal promotionAmount;
        //应付金额
        private BigDecimal payAmount;

        public BigDecimal getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
        }

        public BigDecimal getFreightAmount() {
            return freightAmount;
        }

        public void setFreightAmount(BigDecimal freightAmount) {
            this.freightAmount = freightAmount;
        }

        public BigDecimal getPromotionAmount() {
            return promotionAmount;
        }

        public void setPromotionAmount(BigDecimal promotionAmount) {
            this.promotionAmount = promotionAmount;
        }

        public BigDecimal getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(BigDecimal payAmount) {
            this.payAmount = payAmount;
        }
    }

}
