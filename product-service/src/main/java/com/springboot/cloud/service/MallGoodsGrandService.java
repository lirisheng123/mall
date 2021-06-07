package com.springboot.cloud.service;

import com.springboot.cloud.entity.MallGoodsGrand;

/**
 * @Author: lirisheng
 * @Date: 2021/3/20 17:48
 * @Version 1.0
 */
public interface MallGoodsGrandService {

    /**
     * 添加商品与品牌的关联
     */
    int add (MallGoodsGrand mallGoodsGrand);

    /**
     * 根据商品id查询品牌
     */
    MallGoodsGrand selectGrandByGoodId(Long goodId);

    /**
     * 删除
     */
    int deleteByGoodId(Long goodId);

}
