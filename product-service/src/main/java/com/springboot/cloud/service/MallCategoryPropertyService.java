package com.springboot.cloud.service;

import com.springboot.cloud.entity.MallCategoryProperty;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/3/20 15:20
 * @Version 1.0
 */
public interface MallCategoryPropertyService {

    /**
     * 添加分类的属性
     */
    int add(MallCategoryProperty mallCategoryProperty);

    /**
     * 查看某个分类的所有的属性
     */
    List<MallCategoryProperty> selectByCateId(Long id);

    /**
     * 根据id删除
     */
    int deleteById(Long id);

}
