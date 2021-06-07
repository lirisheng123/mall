package com.springboot.cloud.dao;

import com.springboot.cloud.entity.MallRoleInterface;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface MallRoleInterfaceMapper {
    int deleteByPrimaryKey(Long uiId);

    @Delete("DELETE FROM  mall_role_interface WHERE  role_id=#{roleId}  ")
    int  deleteByRoleId (Long roleId);

    int insert(MallRoleInterface record);

    int insertList(List<MallRoleInterface> mallRoleInterfaces);

    MallRoleInterface selectByPrimaryKey(Long uiId);

    @Select("SELECT inter_name FROM  mall_interface JOIN  mall_role_interface ON (mall_interface.inter_id= mall_role_interface.inter_id )" +
            "WHERE  mall_role_interface.role_id=#{roleId} ")
    List<String> selectInteNameByRoleId(Long roleId);

    List<MallRoleInterface> selectAll();

    int updateByPrimaryKey(MallRoleInterface record);

    @Select("SELECT ui_id  FROM  mall_role_interface WHERE  role_id=#{roleId} Limit 1 ")
    Long selectAnyByRoleId(Long roleId);

    @Select("SELECT ur_id  FROM  mall_role_interface WHERE  inter_id=#{inteId} Limit 1 ")
    Long selectAnyByInterId(Long inteId);

    @Select("SELECT role_name FROM  mall_role JOIN  mall_role_interface ON (mall_role.role_id= mall_role_interface.role_id )" +
            "WHERE  mall_role_interface.inter_id=#{inteId}  ")
    List<String> selectRoleNameByInteId(Long inteId);

}