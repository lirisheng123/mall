package com.springboot.cloud.service.impl;

import com.springboot.cloud.dao.MallUserAddressMapper;
import com.springboot.cloud.entity.MallUserAddress;
import com.springboot.cloud.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 20:25
 * @Version 1.0
 */
@Service
public class UserAddressServiceImpl implements UserAddressService {
    @Autowired
    MallUserAddressMapper mallUserAddressMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(MallUserAddress mallUserAddress) {
        if(mallUserAddress.getDefaultFlag()==1){
            //如果存在默认,则改为0
            Long addreassId1=mallUserAddressMapper.selectAnyDefaultFlagByUserId(mallUserAddress.getUserId());
            if(addreassId1!=null){
                mallUserAddressMapper.updateDefaultFlag(addreassId1,0L);
            }
        }
        return mallUserAddressMapper.insert(mallUserAddress);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(Long userAddressId, MallUserAddress mallUserAddress) {


        mallUserAddress.setAddressId(userAddressId);
        if(mallUserAddress.getDefaultFlag()==1){
            //如果存在默认,则改为0
            Long addreassId1=mallUserAddressMapper.selectAnyDefaultFlagByUserId(mallUserAddress.getUserId());
            if(addreassId1!=null){
                mallUserAddressMapper.updateDefaultFlag(addreassId1,0L);
            }
        }
        mallUserAddressMapper.updateByPrimaryKey(mallUserAddress);
        return  1;
    }

    @Override
    public MallUserAddress select(Long userAddressId) {

        return mallUserAddressMapper.selectByPrimaryKey(userAddressId);
    }

    @Override
    public int delete(Long userAddressId) {
        return mallUserAddressMapper.deleteByPrimaryKey(userAddressId);
    }

    @Override
    public List<MallUserAddress> selectListByUserId(Long userId) {
        return mallUserAddressMapper.selectListByUserId(userId);
    }

    @Override
    @Transactional(rollbackForClassName = "Exception.class")
    public int updateDefaultFlag(Long userId,Long addressId) {

        //如果存在默认,则改为0
        Long addreassId1=mallUserAddressMapper.selectAnyDefaultFlagByUserId(userId);
        if(addreassId1!=null){
            mallUserAddressMapper.updateDefaultFlag(addreassId1,0L);
        }
        //不存在,则直接改为1
        mallUserAddressMapper.updateDefaultFlag(addressId,1L);
        return 1;
    }

    @Override
    public MallUserAddress selectDefaultByUserId(Long userId) {
        return mallUserAddressMapper.selectDefaultByUserId(userId);
    }
}
