package com.springboot.cloud.dao;

import com.springboot.cloud.entity.MallRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MallRoleMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(MallRole record);

    MallRole selectByPrimaryKey(Long roleId);

    @Select("SELECT  * FROM  mall_role WHERE  role_name=#{roleName} ")
    MallRole selectByRoleName(String  roleName);

    List<MallRole> selectAll();

    int updateByPrimaryKey(MallRole record);


}