package com.springboot.cloud.service;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 22:03
 * @Version 1.0
 */
public interface MallUserRoleService {

    /**
     * 根据用户id查询角色名
     */
    List<String> selectRoleNameByUserId(Long userId);

    List<Long>  selectRoleIdsByUserId(Long userId);
     /**
     * 修改用户的角色分配
     */
    int updateRolesByUserId(Long userId,List<Long> rolesId);


}
