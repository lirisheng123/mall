package com.springboot.cloud.dao;



import com.springboot.cloud.entity.MallInterface;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MallInterfaceMapper {
    int deleteByPrimaryKey(Long interId);

    int insert(MallInterface record);

    MallInterface selectByPrimaryKey(Long interId);

    List<MallInterface> selectAll();

    int updateByPrimaryKey(MallInterface record);

    @Select("SELECT  * FROM  mall_interface WHERE  inter_name LIKE concat('%',#{inteName} ,'%') ")
    MallInterface selectByInteName(String inteName);
}