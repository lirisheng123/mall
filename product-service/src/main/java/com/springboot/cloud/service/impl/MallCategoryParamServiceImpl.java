package com.springboot.cloud.service.impl;

import com.springboot.cloud.dao.MallCategoryParamMapper;
import com.springboot.cloud.entity.MallCategoryParam;
import com.springboot.cloud.service.MallCategoryParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/18 17:08
 * @Version 1.0
 */
@Service
public class MallCategoryParamServiceImpl implements MallCategoryParamService {

    @Autowired
    MallCategoryParamMapper mallCategoryParamMapper;
    @Override
    public int create(MallCategoryParam mallCategoryParam) {
        return mallCategoryParamMapper.insert(mallCategoryParam);
    }

    @Override
    public MallCategoryParam selectByCategoryParamId(Long mallCategoryParamId) {
        return mallCategoryParamMapper.selectByPrimaryKey(mallCategoryParamId);
    }

    @Override
    public int deleteByCategoryParamId(Long mallCategoryParamId) {
        return  mallCategoryParamMapper.deleteByPrimaryKey( mallCategoryParamId);
    }

    @Override
    public int updateByCategoryParamId(Long categoryParamId,MallCategoryParam mallCategoryParam) {
        mallCategoryParam.setCategoryParamId(categoryParamId);
        return  mallCategoryParamMapper.updateByPrimaryKey( mallCategoryParam);
    }

    @Override
    public List<MallCategoryParam> selectByCategoryId(Long categoryId) {
        return mallCategoryParamMapper.selectByCategoryId(categoryId);
    }

    @Override
    public  int deleteByCategoryId(Long categoryId ){
        return mallCategoryParamMapper.deleteByCategoryId(categoryId);
    }
}
