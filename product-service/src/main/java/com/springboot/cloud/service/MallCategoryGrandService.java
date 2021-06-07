package com.springboot.cloud.service;

import com.springboot.cloud.dto.GrandAndCateGrandId;
import com.springboot.cloud.entity.MallCategoryGrand;
import com.springboot.cloud.entity.MallGrand;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/18 10:36
 * @Version 1.0
 */
public interface MallCategoryGrandService {

    /**
       批量增加品牌和分类关联关系
     */
    int createList(List<MallCategoryGrand> mallCategoryGrands);

    /**
     * 增加品牌和分类关联关系
     * @return
     */
    int create(MallCategoryGrand mallCategoryGrand);

    /**
      删除某个分类的所有的品牌
     */
    int deleteByCategoryId(Long categoryId);

    /**
       查询某个分类的所有的品牌
     */
    List<GrandAndCateGrandId> selectByCategoryId(Long categoryId);

    /**
     * 删除某个分类的某个品牌
     */
    int deleteById(Long id);

    /**
     更改某个分类的所有的品牌
     */
    int updateByCategoryId(Long categoryId,List<MallCategoryGrand> mallCategoryGrands);







}
