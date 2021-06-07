package com.springboot.cloud.dao;


import com.springboot.cloud.entity.MallCategoryProperty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MallCategoryPropertyMapper {
    int deleteByPrimaryKey(Long categoryPropertyId);

    int insert(MallCategoryProperty record);

    MallCategoryProperty selectByPrimaryKey(Long categoryPropertyId);

    List<MallCategoryProperty> selectAll();

    int updateByPrimaryKey(MallCategoryProperty record);

    @Select("SELECT  * FROM  mall_category_property WHERE  category_id=#{cateId} ")
    List<MallCategoryProperty> selectByCateId (Long cateId);
}