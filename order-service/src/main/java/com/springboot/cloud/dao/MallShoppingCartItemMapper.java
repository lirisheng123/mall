package com.springboot.cloud.dao;


import com.springboot.cloud.entity.MallShoppingCartItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface MallShoppingCartItemMapper {
    int deleteByPrimaryKey(Long cartItemId);

    int insert(MallShoppingCartItem record);

    MallShoppingCartItem selectByPrimaryKey(Long cartItemId);

    List<MallShoppingCartItem> selectAll();

    int updateByPrimaryKey(MallShoppingCartItem record);

    @Select("SELECT  * FROM  mall_shopping_cart_item WHERE  user_id=#{userId} AND " +
            "is_deleted=0")
    List<MallShoppingCartItem> selectByUserId(Long userId);

    @Update("UPDATE mall_shopping_cart_item SET goods_count=#{goodsCount} WHERE  " +
            "cart_item_id=#{cartItemId} ")
    int updateCountByPrimaryKey (MallShoppingCartItem cartItemIds);

    @Update("UPDATE mall_shopping_cart_item SET is_deleted=#{isDeleted}  WHERE  " +
            "user_id=#{userId}  ")
    int updateIsDeleteByUserId(MallShoppingCartItem cartItemIds);


    int updateIsDeleteListByPrimary(Map<String,Object> map);

    List<MallShoppingCartItem> getListByCartIds(Map<String,Object> map);

}