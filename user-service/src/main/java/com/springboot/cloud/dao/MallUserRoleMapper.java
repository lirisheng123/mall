package com.springboot.cloud.dao;

import com.springboot.cloud.entity.MallUserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;
@Mapper
public interface MallUserRoleMapper {
    int deleteByPrimaryKey(Long urId);

    @Delete("DELETE FROM  mall_user_role WHERE  user_id=#{userId} ")
    int  deleteByUserId (Long userId);

    int insert(MallUserRole record);


    int insertList(List<MallUserRole> mallUserRoles);

    MallUserRole selectByPrimaryKey(Long urId);

    @Select("SELECT role_name FROM  mall_role JOIN  mall_user_role ON (mall_role.role_id=mall_user_role.role_id)" +
            "WHERE  mall_user_role.user_id=#{userId} ")
    List<String> selectRoleNameByUserId(Long userId);

    @Select("SELECT mall_role.role_id FROM  mall_role JOIN  mall_user_role ON (mall_role.role_id=mall_user_role.role_id)" +
            "WHERE  mall_user_role.user_id=#{userId} ")
    List<Long> selectRoleIdsByUserId(Long userId);

    List<MallUserRole> selectAll();

    int updateByPrimaryKey(MallUserRole record);

    @Select("SELECT ur_id  FROM  mall_user_role WHERE  role_id=#{roleId} Limit 1 ")
    Long selectAnyByRoleId(Long roleId);
}