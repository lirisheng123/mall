package com.springboot.cloud.service.impl;

import com.springboot.cloud.dao.MallShoppingCartItemMapper;
import com.springboot.cloud.entity.MallShoppingCartItem;
import com.springboot.cloud.service.MallShoppingCartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: lirisheng
 * @Date: 2021/2/23 11:46
 * @Version 1.0
 */
@Service
public class MallShoppingCartItemServiceImpl implements MallShoppingCartItemService {
    @Autowired
    MallShoppingCartItemMapper mallShoppingCartItemMapper;

    @Override
    public int add(MallShoppingCartItem cartItem) {

        return mallShoppingCartItemMapper.insert(cartItem);
    }


    @Override
    public List<MallShoppingCartItem> list(Long userId) {

        return mallShoppingCartItemMapper.selectByUserId(userId);
    }



    @Override
    public int updateQuantity(Long cartItemIds, Integer quantity) {
        MallShoppingCartItem cartItem = new MallShoppingCartItem();
        cartItem.setGoodsCount(quantity);
        cartItem.setCartItemId(cartItemIds);
        return mallShoppingCartItemMapper.updateCountByPrimaryKey(cartItem);
    }

    @Override
    public int delete(Long userId, List<Long> cartItemIds ) {

        Map<String,Object> map = new HashMap<>();
        map.put("isDeleted",1);
        map.put("userId",userId);
        map.put("list",cartItemIds);

        return mallShoppingCartItemMapper.updateIsDeleteListByPrimary(map);
    }
    

//    @Override
//    public int updateAttr(Long cartItemIds, MallShoppingCartItem cartItem) {
//
//        MallShoppingCartItem updateCart = new MallShoppingCartItem();
//        updateCart.setCartItemId(cartItem.getCartItemId());
//
//        return mallShoppingCartItemMapper.updateByPrimaryKey(updateCart);
//    }

    @Override
    public int clear(Long userId) {
        MallShoppingCartItem record = new MallShoppingCartItem();
        record.setIsDeleted(new Integer(1).byteValue());
        record.setUserId(userId);
        return mallShoppingCartItemMapper.updateIsDeleteByUserId(record);
    }

    @Override
    public List<MallShoppingCartItem> getListByCartIds(List<Long> cateIds) {
        List<MallShoppingCartItem> mallShoppingCartItems = new ArrayList<>();
        Map<String, Object> cateMap = new HashMap<String, Object>();
        cateMap.put("cateIds", cateIds);
        return  mallShoppingCartItemMapper.getListByCartIds(cateMap);


    }
}
