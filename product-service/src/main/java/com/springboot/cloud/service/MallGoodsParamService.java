package com.springboot.cloud.service;

import com.springboot.cloud.entity.MallGoodsParam;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/19 11:22
 * @Version 1.0
 */

public interface MallGoodsParamService {

    /**
     * 根据商品id查找商品参数
     */
    List<MallGoodsParam> selectGoodsParamByGoodId(Long  goodId);

    /**
     * 根据id修改商品参数
     */
    int updateByGoodsParamId(Long goodsParamId,MallGoodsParam mallGoodsParam );

    /**
     * 批量添加商品参数
     */
    int createList(List<MallGoodsParam> mallGoodsParams);

    /**
     * 删除
     */
    int deleteByGoodId(Long goodId);








}
