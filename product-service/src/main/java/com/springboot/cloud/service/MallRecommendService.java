package com.springboot.cloud.service;

import com.springboot.cloud.entity.MallRecommend;

/**
 * @Author: lirisheng
 * @Date: 2021/5/3 21:23
 * @Version 1.0
 */
public interface MallRecommendService {

    /**
     * 添加推荐的记录
     */
    int create(MallRecommend mallRecommend);

    /**
     * 根据用户id和商品id获取记录
     */
    MallRecommend selectByUserIdAndGoodId(Long userId,Long goodId);

    /**
     * 更改,即添加喜好的分数
     */
    int update(Long id,MallRecommend mallRecommend);
}
