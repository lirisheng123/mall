package com.springboot.cloud.service.impl;

import com.springboot.cloud.dao.MallCategoryPropertyMapper;
import com.springboot.cloud.entity.MallCategoryProperty;
import com.springboot.cloud.service.MallCategoryParamService;
import com.springboot.cloud.service.MallCategoryPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/3/20 15:21
 * @Version 1.0
 */
@Service
public class MallCategoryPropertyServiceImp implements MallCategoryPropertyService {

    @Autowired
    MallCategoryPropertyMapper mallCategoryPropertyMapper;

    @Override
    public int add(MallCategoryProperty mallCategoryProperty) {
        return mallCategoryPropertyMapper.insert(mallCategoryProperty);
    }

    @Override
    public List<MallCategoryProperty> selectByCateId(Long id) {
        return mallCategoryPropertyMapper.selectByCateId(id);
    }

    @Override
    public int deleteById(Long id) {
        return mallCategoryPropertyMapper.deleteByPrimaryKey(id);
    }
}
