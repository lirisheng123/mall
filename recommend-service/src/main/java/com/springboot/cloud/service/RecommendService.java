package com.springboot.cloud.service;

import com.springboot.cloud.entity.MallGoodsInfo;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/5/5 18:18
 * @Version 1.0
 */

public interface RecommendService {

     List<MallGoodsInfo> slopeOneRecommender(Long userId, Integer size);

    List<MallGoodsInfo> userBasedRecommender(Long userId, Integer size);

    List<MallGoodsInfo> itemBasedRecommender(Long userId, Integer size);


}
