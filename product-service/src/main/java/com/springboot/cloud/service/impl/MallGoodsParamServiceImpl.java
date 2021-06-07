package com.springboot.cloud.service.impl;

import com.springboot.cloud.dao.MallGoodsParamMapper;
import com.springboot.cloud.entity.MallGoodsParam;
import com.springboot.cloud.service.MallGoodsParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/19 11:23
 * @Version 1.0
 */
@Service
public class MallGoodsParamServiceImpl implements MallGoodsParamService {

    @Autowired
    MallGoodsParamMapper mallGoodsParamMapper;

    @Override
    public List<MallGoodsParam> selectGoodsParamByGoodId(Long goodId) {

        List<MallGoodsParam> mallGoodsParams =  mallGoodsParamMapper.selectByGoodId(goodId);
        System.out.println("mallGoodsParams"+mallGoodsParams);
        return mallGoodsParams;
    }

    @Override
    public int updateByGoodsParamId(Long goodsParamId, MallGoodsParam mallGoodsParam) {

        mallGoodsParam.setGoodsParamId(goodsParamId);
        int count = mallGoodsParamMapper.updateByPrimaryKey(mallGoodsParam);
        System.out.println("count"+count);
        return mallGoodsParamMapper.updateByPrimaryKey(mallGoodsParam);
    }

    @Override
    public int createList(List<MallGoodsParam> mallGoodsParams) {
        return mallGoodsParamMapper.insertList(mallGoodsParams);
    }

    @Override
    public int deleteByGoodId(Long goodId) {
        return mallGoodsParamMapper.deleteByGoodId(goodId);
    }
}
