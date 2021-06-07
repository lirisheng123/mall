package com.springboot.cloud.dao;



import com.springboot.cloud.entity.MallCategoryParam;
import com.springboot.cloud.entity.MallGoodsParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MallCategoryParamMapper {
    int deleteByPrimaryKey(Long categoryParamId);

    int insert(MallCategoryParam record);

    MallCategoryParam selectByPrimaryKey(Long categoryParamId);

    List<MallCategoryParam> selectAll();

    int updateByPrimaryKey(MallCategoryParam record);

    @Select("SELECT  * FROM  mall_category_param WHERE  category_id = #{categoryId} ")
    List<MallCategoryParam> selectByCategoryId(Long categoryId);

    @Delete("DELETE FROM  mall_category_param WHERE  category_id = #{categoryId} ")
    int deleteByCategoryId(Long categoryId);

    int insertAll(List<MallGoodsParam> mallGoodsParams);
}