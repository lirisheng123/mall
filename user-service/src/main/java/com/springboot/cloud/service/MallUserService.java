package com.springboot.cloud.service;

import com.springboot.cloud.dto.UserParam;
import com.springboot.cloud.dto.UserRoleParam;
import com.springboot.cloud.entity.MallUser;
import com.springboot.cloud.entity.MallUserRole;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 20:02
 * @Version 1.0
 */
public interface MallUserService {

    /**
     * 添加用户
     */
    int create(UserRoleParam userRoleParam);

    /**
     * 修改用户
     */
    int updateByUserId(Long userId,UserRoleParam userRoleParam);

    /**
     * 批量修改锁定或开锁用户账号
     */
    int updateLock(List<Long> userId, Long statusLocked);

    /**
     * 删除用户
     */
    int delete(Long userId);

    /**
     * 根据用户id查询用户
     */
    UserRoleParam selectByUserId(Long userId);

    /**
     * 多个参数来分页查询用户
     */
    List<UserRoleParam> selectList(UserParam userParam, Integer pageSize, Integer pageNum);

    UserRoleParam selectRolesByUsername(String username);


    int updateRoleListByUserId(Long userId,List<Long> roleId);

    int registerUser(MallUser mallUser);

}
