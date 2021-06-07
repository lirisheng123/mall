package com.springboot.cloud.service;

import com.springboot.cloud.dto.MallRoleIntefaceParam;
import com.springboot.cloud.dto.MallRoleNameIntefaceName;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 20:04
 * @Version 1.0
 */
public interface RoleService {

    /**
     * 添加角色
     */
    int create(MallRoleIntefaceParam mallRoleIntefaceParam) ;

    /**
     * 根据角色id修改角色
     */
     int update(Long roleId,MallRoleIntefaceParam mallRoleIntefaceParam);
    /**
     * 删除角色
     */
    int  delete(Long roleId);

    /**
     * 根据roleId来查询角色
     */
    MallRoleIntefaceParam  selectByRoleId(Long roleId);

    /**
     * 查询所有角色
     */
    List<MallRoleIntefaceParam> list();

    /**
     * 通过角色名来查询接口组
     */
    List<String> selectIntefacesByRoleName(String roleName);

    /**
     * 通过接口名来查询角色名组
     */
    List<String> selectRoleNameByIntefaces( String inteName);

    /**
     * 查询所有的接口和其相对应的role
     * @return
     */
    List<MallRoleNameIntefaceName> selectAllRoleAndInteName();
}
