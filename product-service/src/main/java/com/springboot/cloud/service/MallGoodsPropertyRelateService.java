package com.springboot.cloud.service;

import com.springboot.cloud.entity.MallGoodsPropertyRelate;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/3/21 17:12
 * @Version 1.0
 */
public interface MallGoodsPropertyRelateService {
    /**
     * 批量增加
     */
    int addList(List<MallGoodsPropertyRelate> mallGoodsPropertyRelates);

    /**
     * 查找所有的googPropertyRelate
     */
    List<MallGoodsPropertyRelate> selectByGoodId(Long goodId);

    /**
     * 删除
     */
    int deleteByGoodId(Long goodId);

}
