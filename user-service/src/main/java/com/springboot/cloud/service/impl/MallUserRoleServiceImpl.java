package com.springboot.cloud.service.impl;

import com.springboot.cloud.dao.MallUserRoleMapper;
import com.springboot.cloud.entity.MallUserRole;
import com.springboot.cloud.service.MallUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 22:03
 * @Version 1.0
 */
@Service
public class MallUserRoleServiceImpl implements MallUserRoleService {

    @Autowired
    MallUserRoleMapper mallUserRoleMapper;




    @Override
    public List<String> selectRoleNameByUserId(Long userId) {
        List<String> roleName = mallUserRoleMapper.selectRoleNameByUserId(userId);
        return roleName;
    }

    @Override
    public List<Long> selectRoleIdsByUserId(Long userId) {
        return mallUserRoleMapper.selectRoleIdsByUserId(userId);
    }

    @Override
    @Transactional(rollbackForClassName = "Exception.class")
    public int updateRolesByUserId(Long userId, List<Long> rolesId) {
        //删除该用户与角色的所有关系
        mallUserRoleMapper.deleteByUserId(userId);
        //在插入新的关系
        Iterator<Long> iterator = rolesId.iterator();
        List<MallUserRole> mallUserRoles = new ArrayList<>();
        while(iterator.hasNext()){
            MallUserRole mallUserRole = new MallUserRole();
            mallUserRole.setUserId(userId);
            mallUserRole.setRoleId(iterator.next());
            //这里默认1为管理员
            mallUserRole.setCreateUser(1);
            mallUserRole.setCreateUser(1);
            mallUserRoles.add(mallUserRole);
        }
        mallUserRoleMapper.insertList(mallUserRoles);
        return 1;
    }
}
