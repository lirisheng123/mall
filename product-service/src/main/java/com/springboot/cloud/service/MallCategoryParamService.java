package com.springboot.cloud.service;

import com.springboot.cloud.entity.MallCategoryParam;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/18 16:58
 * @Version 1.0
 */
public interface MallCategoryParamService {

    /**
     * 添加分类与参数的关联关系
     * @param mallCategoryParam
     * @return
     */
    int create(MallCategoryParam mallCategoryParam);

    /**
     * 根据id选择分类与参数的关联关系
     * @param mallCategoryParamId
     * @return
     */
    MallCategoryParam selectByCategoryParamId(Long mallCategoryParamId);

    /**
     * 根据id删除分类与参数的关联关系
     * @param mallCategoryParamId
     * @return
     */
    int deleteByCategoryParamId(Long mallCategoryParamId);



    /**
     * 根据id更改分类与参数的关联关系
     * @param mallCategoryParam
     * @return
     */
    int updateByCategoryParamId(Long categoryParamId,MallCategoryParam mallCategoryParam);

    /**
     * 根据分类的id选择分类与参数的关联关系
     * @param categoryId
     * @return
     */
    List<MallCategoryParam> selectByCategoryId(Long categoryId );

    /**
     * 根据分类的id选择分类与参数的关联关系
     * @param categoryId
     * @return
     */
    int deleteByCategoryId(Long categoryId );


}
