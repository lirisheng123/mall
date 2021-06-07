package com.springboot.cloud.service.impl;

import com.springboot.cloud.dao.MallRoleMapper;
import com.springboot.cloud.dao.MallUserRoleMapper;
import com.springboot.cloud.dto.MallRoleIntefaceParam;
import com.springboot.cloud.dto.MallRoleNameIntefaceName;
import com.springboot.cloud.entity.MallInterface;
import com.springboot.cloud.entity.MallRole;

import com.springboot.cloud.entity.MallRoleInterface;
import com.springboot.cloud.service.MallInterfaceService;
import com.springboot.cloud.service.MallRoleInterfaceService;
import com.springboot.cloud.service.RoleService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 20:04
 * @Version 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    MallRoleMapper mallRoleMapper;

    @Autowired
    MallRoleInterfaceService mallRoleInterfaceService;

    @Autowired
    MallUserRoleMapper mallUserRoleMapper;

    @Autowired
    MallInterfaceService mallInterfaceService;


    @Override
    @Transactional(rollbackForClassName = "Exception.class")
    public int create(MallRoleIntefaceParam mallRoleIntefaceParam)
    {
        //插入角色
        MallRole mallRole = mallRoleIntefaceParam;
        mallRoleMapper.insert(mallRole);
        //插入角色与接口的关系
        List<MallRoleInterface> mallRoleInterfaces = new ArrayList<>();
        Iterator<Long> mallInterfaceIds = mallRoleIntefaceParam.getMallInterfacenIds().iterator();
        while(mallInterfaceIds.hasNext()){
            MallRoleInterface mallRoleInterface = new MallRoleInterface();
            mallRoleInterface.setRoleId(mallRole.getRoleId());
            mallRoleInterface.setInterId(mallInterfaceIds.next());
            mallRoleInterface.setCreateUser(mallRole.getCreateUser());
            mallRoleInterface.setUpdateUser(mallRole.getUpdateUser());
            mallRoleInterfaces.add(mallRoleInterface);
        }
        return mallRoleInterfaceService.insertList(mallRoleInterfaces);
    }

    @Override
    @Transactional(rollbackForClassName = "Exception.class")
    public int update(Long roleId,MallRoleIntefaceParam mallRoleIntefaceParam) {
        MallRole mallRole = mallRoleIntefaceParam;
        mallRole.setRoleId(roleId);
        mallRoleMapper.updateByPrimaryKey(mallRole);
        List<Long> intefaceIds  = mallRoleIntefaceParam.getMallInterfacenIds();
       return mallRoleInterfaceService.updateIntefaceByRoleId(roleId,intefaceIds);
    }

    @Override
    @Transactional(rollbackForClassName = "Exception.class")
    public int delete(Long roleId) {
        //查看user-role是否存在关联,存在,则不行
         if(mallUserRoleMapper.selectAnyByRoleId(roleId)!=null){
             return 0;
         }
         mallRoleMapper.deleteByPrimaryKey(roleId);
         mallRoleInterfaceService.deleteIntefaceByRoleId(roleId);
        //删除角色
        return 1;
    }

    @Override
    public MallRoleIntefaceParam selectByRoleId(Long roleId) {
        MallRole   mallRole=mallRoleMapper.selectByPrimaryKey(roleId);
        if(mallRole==null){
            return  null;
        }
        List<String> mallInterfaceNames = mallRoleInterfaceService.selectInteNameByRoleId(roleId);
        MallRoleIntefaceParam mallRoleIntefaceParam = new MallRoleIntefaceParam();
        mallRoleIntefaceParam.setRoleId(mallRole.getRoleId());
        mallRoleIntefaceParam.setRoleName(mallRole.getRoleName());
        mallRoleIntefaceParam.setRoleInfo(mallRole.getRoleInfo());
        //需求进行改变
        mallRoleIntefaceParam.setMallInterfacenNames(mallInterfaceNames);
        return mallRoleIntefaceParam;
    }

    @Override
    public List<MallRoleIntefaceParam> list() {

        Iterator<MallRole> mallRoles = mallRoleMapper.selectAll().iterator();
        if(mallRoles==null){
            return  null;
        }
        List<MallRoleIntefaceParam> mallRoleIntefaceParams = new ArrayList<>();
        while(mallRoles.hasNext()){
            MallRoleIntefaceParam mallRoleIntefaceParam = new MallRoleIntefaceParam();
            MallRole mallRole = mallRoles.next();
            mallRoleIntefaceParam.setRoleId(mallRole.getRoleId());
            mallRoleIntefaceParam.setRoleName(mallRole.getRoleName());
            mallRoleIntefaceParam.setRoleInfo(mallRole.getRoleInfo());
            mallRoleIntefaceParam.setCreateTime(mallRole.getCreateTime());
            mallRoleIntefaceParam.setUpdateTime(mallRole.getUpdateTime());
            mallRoleIntefaceParam.setUpdateUser(mallRole.getUpdateUser());
            mallRoleIntefaceParam.setCreateUser(mallRole.getCreateUser());
            List<String> mallInterfaceNames = mallRoleInterfaceService.selectInteNameByRoleId(mallRole.getRoleId());
            mallRoleIntefaceParam.setMallInterfacenNames(mallInterfaceNames);
            mallRoleIntefaceParams.add(mallRoleIntefaceParam);
        }

        return mallRoleIntefaceParams;
    }

    @Override
    public List<String> selectIntefacesByRoleName(String roleName){
        MallRole   mallRole=mallRoleMapper.selectByRoleName(roleName);
        if(mallRole==null){
            return  null;
        }
        List<String> mallInterfaceNames = mallRoleInterfaceService.selectInteNameByRoleId(mallRole.getRoleId());

        return mallInterfaceNames;
    }

    @Override
    public  List<String> selectRoleNameByIntefaces( String inteName){
        MallInterface mallInterface = mallInterfaceService.selectByInteName(inteName);
        if(mallInterface==null){
            return  null;
        }
        List<String> mallRoleName = mallRoleInterfaceService.selectRoleNameByInteId(mallInterface.getInterId());

        return mallRoleName;
    }

    @Override
    public  List<MallRoleNameIntefaceName> selectAllRoleAndInteName(){
        List<MallRoleNameIntefaceName> mallRoleNameIntefaceNames = new ArrayList<>();
         Iterator iterator  = mallInterfaceService.selectAll().stream().map(MallInterface::getInterName)
                .collect(Collectors.toList()).iterator();
         while(iterator.hasNext()){
             MallRoleNameIntefaceName mallRoleNameIntefaceName = new MallRoleNameIntefaceName();
             String inteName = (String)iterator.next();
             mallRoleNameIntefaceName.setIntefaceName(inteName);
             mallRoleNameIntefaceName.setRoleName(selectRoleNameByIntefaces(inteName));
             mallRoleNameIntefaceNames.add(mallRoleNameIntefaceName);
         }
         return  mallRoleNameIntefaceNames;


    }
}


