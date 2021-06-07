package com.springboot.cloud.service.impl;

import com.springboot.cloud.dao.MallRecommendMapper;
import com.springboot.cloud.entity.MallRecommend;
import com.springboot.cloud.service.MallRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lirisheng
 * @Date: 2021/5/3 21:27
 * @Version 1.0
 */
@Service
public class MallRecommendServiceImpl implements MallRecommendService {

    @Autowired
    MallRecommendMapper mallRecommendMapper;
    @Override
    public int create(MallRecommend mallRecommend) {
        return mallRecommendMapper.insert(mallRecommend);
    }

    @Override
    public MallRecommend selectByUserIdAndGoodId(Long userId, Long goodId) {
        return mallRecommendMapper.selectByUserIdAndGoodId(userId,goodId);
    }

    @Override
    public int update(Long id, MallRecommend mallRecommend) {
        mallRecommend.setId(id);
        return mallRecommendMapper.updateByPrimaryKey(mallRecommend);
    }
}
