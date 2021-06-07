package com.springboot.cloud.dao;


import com.springboot.cloud.dto.MallGrandQueryParam;
import com.springboot.cloud.entity.MallGrand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MallGrandMapper {
    int deleteByPrimaryKey(Long grandId);

    int insert(MallGrand record);

    MallGrand selectByPrimaryKey(Long grandId);

    @Select("SELECT  grand_name FROM  mall_grand WHERE  grand_id=#{grandId} ")
    String selectGrandNameByPrimaryKey(Long grandId);

    List<MallGrand> selectAll();

    int updateByPrimaryKey(MallGrand record);

    List<MallGrand>  selectByNamePage(MallGrandQueryParam mallGrandQueryParam);
}