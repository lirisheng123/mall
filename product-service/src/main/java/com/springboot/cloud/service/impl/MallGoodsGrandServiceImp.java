package com.springboot.cloud.service.impl;

import com.springboot.cloud.dao.MallGoodsGrandMapper;
import com.springboot.cloud.entity.MallGoodsGrand;
import com.springboot.cloud.service.MallGoodsGrandService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lirisheng
 * @Date: 2021/3/20 17:48
 * @Version 1.0
 */
@Service
public class MallGoodsGrandServiceImp implements MallGoodsGrandService {

    @Autowired
    MallGoodsGrandMapper mallGoodsGrandMapper;

    @Override
    public int add(MallGoodsGrand mallGoodsGrand) {
        return mallGoodsGrandMapper.insert(mallGoodsGrand) ;
    }

    @Override
    public MallGoodsGrand selectGrandByGoodId(Long goodId) {
        return mallGoodsGrandMapper.selectGrandByGoodId(goodId);
    }


    @Override
    public int deleteByGoodId(Long goodId) {
        return mallGoodsGrandMapper.deleteByGoodId(goodId);
    }
}

