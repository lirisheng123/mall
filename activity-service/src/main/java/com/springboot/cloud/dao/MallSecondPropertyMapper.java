package com.springboot.cloud.dao;


import com.springboot.cloud.entity.MallSecondProperty;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MallSecondPropertyMapper {
    int deleteByPrimaryKey(Long mallSecondPropertyId);

    int insert(MallSecondProperty record);

    MallSecondProperty selectByPrimaryKey(Long mallSecondPropertyId);

    List<MallSecondProperty> selectAll();

    int updateByPrimaryKey(MallSecondProperty record);

    @Select("SELECT  * FROM  mall_second_property WHERE  mall_flash_promotion_id=#{flashId} ")
    List<MallSecondProperty> selectByFlashId(Long flashId);

    @Delete("DELETE  FROM  mall_second_property WHERE  mall_flash_promotion_id=#{flashId} ")
    int deleteByFlashId(Long flashId);
}