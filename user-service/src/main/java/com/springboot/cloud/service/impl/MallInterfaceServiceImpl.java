package com.springboot.cloud.service.impl;

import com.springboot.cloud.dao.MallInterfaceMapper;
import com.springboot.cloud.dao.MallRoleInterfaceMapper;
import com.springboot.cloud.entity.MallInterface;
import com.springboot.cloud.service.MallInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 23:08
 * @Version 1.0
 */
@Service
public class MallInterfaceServiceImpl implements MallInterfaceService {
    @Autowired
    MallInterfaceMapper mallInterfaceMapper;

    @Autowired
    MallRoleInterfaceMapper mallRoleInterfaceMapper;

    @Override
    public int create(MallInterface mallInterface) {
         return mallInterfaceMapper.insert(mallInterface);
    }

    @Override
    public int update(Long inteId, MallInterface mallInterface) {
        mallInterface.setInterId(inteId);
        return mallInterfaceMapper.updateByPrimaryKey(mallInterface);
    }

    @Override
    public int delete(Long inteId) {
        //查看role-interface是否存在关联,存在,则不行
        if(mallRoleInterfaceMapper.selectAnyByInterId(inteId)!=null){
            return 0;
        }

        //删除角色
        return delete(inteId);
    }

    @Override
    public MallInterface selectByInteId(Long inteId) {
        return mallInterfaceMapper.selectByPrimaryKey(inteId);
    }

    @Override
    public List<MallInterface> list() {
        return mallInterfaceMapper.selectAll();
    }

    @Override
    public  MallInterface selectByInteName(String inteName){
        return  mallInterfaceMapper.selectByInteName(inteName);
    }

    @Override
    public List<MallInterface> selectAll(){
        return  mallInterfaceMapper.selectAll();
    }
}
