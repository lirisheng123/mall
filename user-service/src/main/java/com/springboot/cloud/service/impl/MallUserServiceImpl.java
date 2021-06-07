package com.springboot.cloud.service.impl;

import com.github.pagehelper.PageHelper;

import com.springboot.cloud.dao.MallUserMapper;
import com.springboot.cloud.dao.MallUserRoleMapper;
import com.springboot.cloud.dto.UserParam;
import com.springboot.cloud.dto.UserRoleParam;
import com.springboot.cloud.entity.MallUser;
import com.springboot.cloud.entity.MallUserRole;

import com.springboot.cloud.service.MallUserRoleService;
import com.springboot.cloud.service.MallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 20:02
 * @Version 1.0
 */
@Service
public class MallUserServiceImpl implements MallUserService {

    @Autowired
    MallUserMapper mallUserMapper;

    @Autowired
    MallUserRoleMapper mallUserRoleMapper;

    @Autowired
    MallUserRoleService mallUserRoleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional(rollbackForClassName = "Exception.class")
    public int create(UserRoleParam userRoleParam) {
        int count=1;
        MallUser mallUser = userRoleParam;
        //注意,把密码加密
        mallUser.setPasswordMd5(passwordEncoder.encode(mallUser.getPasswordMd5()));
        //插入用户账号
        mallUserMapper.insert(mallUser);
        //添加用户与角色之间的关系
//        Iterator<Long> roleIds = userRoleParam.getRoleIds().iterator();
//        List<MallUserRole>  mallUserRoles = new ArrayList<>();
//        while(roleIds.hasNext()){
//            MallUserRole mallUserRole = new MallUserRole();
//            mallUserRole.setUserId(mallUser.getUserId());
//            mallUserRole.setCreateUser(mallUser.getUserId().intValue());
//            mallUserRole.setUpdateUser(mallUser.getUserId().intValue());
//            mallUserRole.setRoleId(roleIds.next());
//            mallUserRoles.add(mallUserRole);
//        }
//        mallUserRoleMapper.insertList(mallUserRoles);
        return count;
    }



    @Override
    @Transactional(rollbackForClassName = "Exception.class")
    public int updateByUserId(Long userId,UserRoleParam userRoleParam) {
        MallUser mallUser = userRoleParam;
        mallUser.setUserId(userId);
        //注意,把密码加密
        mallUser.setPasswordMd5(passwordEncoder.encode(mallUser.getPasswordMd5()));
        mallUserMapper.updateByPrimaryKey(mallUser);
//        List<Long> roleIds= userRoleParam.getRoleIds();
//        mallUserRoleService.updateRolesByUserId(userId,roleIds);
        return 1;
    }

    @Override
    public int updateLock(List<Long> userId, Long statusLocked) {
        Map<String,Object> map = new HashMap<>();
        map.put("Status",statusLocked);
        map.put("list",userId);
        return mallUserMapper.updateLockStatusByUserId(map);
    }

    @Override
    public int delete(Long userId) {
        return  mallUserMapper.updateDeleteByUserId(userId);
    }

    @Override
    public UserRoleParam selectByUserId(Long userId) {
        MallUser mallUser = mallUserMapper.selectByPrimaryKey(userId);
        if(mallUser==null){
            return  null;
        }
        List<String> roleNames = mallUserRoleService.selectRoleNameByUserId(userId);
        UserRoleParam userRoleParam = new UserRoleParam();
        userRoleParam.setUserId(mallUser.getUserId());
        userRoleParam.setLoginName(mallUser.getLoginName());
        userRoleParam.setPasswordMd5(mallUser.getPasswordMd5());
        userRoleParam.setPhoneNumber(mallUser.getPhoneNumber());
        userRoleParam.setIsDeleted(mallUser.getIsDeleted());
        userRoleParam.setLockedFlag(mallUser.getLockedFlag());
        userRoleParam.setRoleNames(roleNames);
        return  userRoleParam;
    }

    @Override
    public List<UserRoleParam> selectList(UserParam userParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<UserRoleParam> userRoleParams = new ArrayList<>();
        Iterator<MallUser> mallUsers = mallUserMapper.selectPageByManyCondition(userParam).iterator();
        if(mallUsers==null){
            return  null;
        }
        while(mallUsers.hasNext()){
            UserRoleParam userRoleParam = new UserRoleParam();
            MallUser mallUser = mallUsers.next();
            userRoleParam.setUserId(mallUser.getUserId());
            userRoleParam.setLoginName(mallUser.getLoginName());
            userRoleParam.setPasswordMd5(mallUser.getPasswordMd5());
            userRoleParam.setPhoneNumber(mallUser.getPhoneNumber());
            userRoleParam.setIsDeleted(mallUser.getIsDeleted());
            userRoleParam.setLockedFlag(mallUser.getLockedFlag());
            userRoleParam.setCreateTime(mallUser.getCreateTime());
            List<String> roleNames = mallUserRoleService.selectRoleNameByUserId(mallUser.getUserId());
            List<Long> roleIds = mallUserRoleService.selectRoleIdsByUserId(mallUser.getUserId());
            userRoleParam.setRoleNames(roleNames);
            userRoleParam.setRoleIds(roleIds);
            userRoleParams.add(userRoleParam);
        }
        return  userRoleParams;
    }

    @Override
    public UserRoleParam selectRolesByUsername(String username) {
        UserRoleParam userRoleParam = new UserRoleParam();

        MallUser mallUser = mallUserMapper.selectByLoginName(username);
        if(mallUser==null){
            return  null;
        }
    userRoleParam.setUserId(mallUser.getUserId());
    userRoleParam.setLoginName(mallUser.getLoginName());
    userRoleParam.setPasswordMd5(mallUser.getPasswordMd5());
    userRoleParam.setPhoneNumber(mallUser.getPhoneNumber());
    userRoleParam.setIsDeleted(mallUser.getIsDeleted());
    userRoleParam.setLockedFlag(mallUser.getLockedFlag());
    List<String> roleNames = mallUserRoleService.selectRoleNameByUserId(mallUser.getUserId());
    userRoleParam.setRoleNames(roleNames);



        return  userRoleParam;
    }

    @Override
    public int updateRoleListByUserId(Long userId, List<Long> roleIds) {


        return mallUserRoleService.updateRolesByUserId(userId,roleIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int registerUser(MallUser mallUser) {
        //注意,把密码加密
        mallUser.setPasswordMd5(passwordEncoder.encode(mallUser.getPasswordMd5()));
        //插入用户账号
        mallUser.setLockedFlag(new Byte("0"));
       mallUserMapper.insert(mallUser);
        //默认为用户角色
        MallUserRole mallUserRole = new MallUserRole();
        mallUserRole.setUserId(mallUser.getUserId());
        mallUserRole.setRoleId(4L);
        mallUserRole.setUpdateUser(1);
        mallUserRole.setCreateUser(1);
        mallUserRoleMapper.insert(mallUserRole);
        return 1;
    }
}
