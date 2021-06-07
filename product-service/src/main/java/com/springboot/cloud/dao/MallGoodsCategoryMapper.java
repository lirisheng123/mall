package com.springboot.cloud.dao;



import com.springboot.cloud.dto.CategoryParentAndChird;
import com.springboot.cloud.entity.MallGoodsCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MallGoodsCategoryMapper {
    int deleteByPrimaryKey(Long categoryId);

    int insert(MallGoodsCategory record);

    MallGoodsCategory selectByPrimaryKey(Long categoryId);

    List<MallGoodsCategory> selectAll();

    @Select("SELECT  * FROM  mall_goods_category WHERE  parent_id= #{parentId} ")
    List<MallGoodsCategory> selectByLevel(Long parentId);

    int updateByPrimaryKey(MallGoodsCategory record);

    @Select("SELECT  category_id  FROM  mall_goods_category WHERE  parent_id = #{id} ")
    List<Long> selectParentById(Long id);

    /**
     * 获取商品分类及其子分类
     */
    List<CategoryParentAndChird> listWithChildren();
}