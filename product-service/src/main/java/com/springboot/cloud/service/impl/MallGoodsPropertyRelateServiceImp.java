package com.springboot.cloud.service.impl;

import com.springboot.cloud.dao.MallGoodsPropertyRelateMapper;
import com.springboot.cloud.entity.MallGoodsPropertyRelate;
import com.springboot.cloud.service.MallGoodsPropertyRelateService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/3/21 17:13
 * @Version 1.0
 */
@Service
public class MallGoodsPropertyRelateServiceImp implements MallGoodsPropertyRelateService {

    @Autowired
    MallGoodsPropertyRelateMapper mallGoodsPropertyRelateMapper;

    @Override
    public int addList(List<MallGoodsPropertyRelate> mallGoodsPropertyRelates) {
        return  mallGoodsPropertyRelateMapper.insertAll(mallGoodsPropertyRelates);
    }

    @Override
    public List<MallGoodsPropertyRelate> selectByGoodId(Long goodId) {
        return mallGoodsPropertyRelateMapper.selectByGoodId(goodId);
    }

    @Override
    public int deleteByGoodId(Long goodId) {
        return mallGoodsPropertyRelateMapper.deleteByGoodId(goodId);
    }
}
