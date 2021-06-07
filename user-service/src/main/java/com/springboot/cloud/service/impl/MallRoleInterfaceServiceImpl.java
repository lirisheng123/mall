package com.springboot.cloud.service.impl;

import com.springboot.cloud.dao.MallRoleInterfaceMapper;
import com.springboot.cloud.entity.MallRoleInterface;
import com.springboot.cloud.service.MallRoleInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 22:35
 * @Version 1.0
 */
@Service
public class MallRoleInterfaceServiceImpl implements MallRoleInterfaceService {
   @Autowired
    MallRoleInterfaceMapper mallRoleInterfaceMapper;

    @Override
    public List<String> selectInteNameByRoleId(Long roleId) {
       return mallRoleInterfaceMapper.selectInteNameByRoleId(roleId);
    }

    @Override
    @Transactional(rollbackForClassName = "Exception.class")
    public int updateIntefaceByRoleId(Long roleId, List<Long> intefaceId) {
        //删除该角色与接口的所有关系
        mallRoleInterfaceMapper.deleteByRoleId(roleId);
        //在插入新的关系
        Iterator<Long> iterator = intefaceId.iterator();
        List<MallRoleInterface> mallRoleInterfaces = new ArrayList<>();
        while(iterator.hasNext()){
            MallRoleInterface mallRoleInterface = new MallRoleInterface();
            mallRoleInterface.setRoleId(roleId);
            mallRoleInterface.setInterId(iterator.next());
            //这里默认1为管理员
            mallRoleInterface.setCreateUser(1);
            mallRoleInterface.setCreateUser(1);
            mallRoleInterfaces.add(mallRoleInterface);
        }
        mallRoleInterfaceMapper.insertList(mallRoleInterfaces);
        return 1;
    }

    @Override
    public int insertList(List<MallRoleInterface> mallRoleInterfaces) {
        return mallRoleInterfaceMapper.insertList(mallRoleInterfaces);
    }

    @Override
    public Long selectAnyByRoleId(Long roleId) {
        return mallRoleInterfaceMapper.selectAnyByRoleId(roleId);
    }

    @Override
    public int deleteIntefaceByRoleId(Long roleId) {
        return  mallRoleInterfaceMapper.deleteByRoleId(roleId);
    }

    @Override
    public List<String> selectRoleNameByInteId(Long inteId) {
        return mallRoleInterfaceMapper.selectRoleNameByInteId(inteId);
    }
}
