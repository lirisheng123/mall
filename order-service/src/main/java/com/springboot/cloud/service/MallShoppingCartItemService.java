package com.springboot.cloud.service;

import com.springboot.cloud.entity.MallShoppingCartItem;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/23 11:46
 * @Version 1.0
 */
public interface MallShoppingCartItemService {

    /**
     * 查询购物车中是否包含该商品，有增加数量，无添加到购物车
     */
    @Transactional
    int add(MallShoppingCartItem cartItem);

    /**
     * 根据会员编号获取购物车列表
     */
    List<MallShoppingCartItem> list(Long userId);

    /**
     * 获取包含促销活动信息的购物车列表
     */
//    List<CartPromotionItem> listPromotion(Long memberId, List<Long> cartIds);

    /**
     * 修改某个购物车商品的数量
     */
    int updateQuantity(Long cartItemIds,Integer quantity);

    /**
     * 批量删除购物车中的商品
     */
    int delete(Long userId, List<Long> cartItemIds);

    /**
     *获取购物车中用于选择商品规格的商品信息
     */
//    int  getCartProduct(Long productId);

    /**
     * 修改购物车中商品的规格
     */
//    @Transactional
//    int updateAttr(Long cartItemIds, MallShoppingCartItem cartItem);

    /**
     * 清空购物车
     */
    int clear(Long userId);

    List<MallShoppingCartItem> getListByCartIds(List<Long> cateIds);
}
