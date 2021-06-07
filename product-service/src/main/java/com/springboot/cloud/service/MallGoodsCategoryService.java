package com.springboot.cloud.service;


import com.springboot.cloud.dto.CategoryGrand;
import com.springboot.cloud.dto.CategoryParentAndChird;
import com.springboot.cloud.entity.MallGoodsCategory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品分类管理Service
 * Created by macro on 2018/4/26.
 */
public interface MallGoodsCategoryService {
    /**
     * 创建商品分类
     */
    @Transactional
    int create(CategoryGrand categoryGrand );

    /**
     * 修改商品分类
     */
    @Transactional
    int update(Long id,  MallGoodsCategory mallGoodsCategory);

    /**
     * 分页获取商品分类
     */
    List<MallGoodsCategory> getList(Long parentId, Integer pageSize, Integer pageNum);

    /**
     * 删除商品分类
     */
    int delete(Long id);

    /**
     * 根据ID获取商品分类
     */
    MallGoodsCategory getItem(Long id);

    /**
     * 判断是否可以进行删除
     */
    int judgeDelete(Long id);


//
//    /**
//     * 批量修改导航状态
//     */
//    int updateNavStatus(List<Long> ids, Integer navStatus);
//
//    /**
//     * 批量修改显示状态
//     */
//    int updateShowStatus(List<Long> ids, Integer showStatus);
//
     /**
     * 以层级形式获取商品分类
     */
     List<CategoryParentAndChird> listWithChildren();
}
