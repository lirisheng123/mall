package com.springboot.cloud.service;

import com.springboot.cloud.entity.MallUserAddress;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 20:25
 * @Version 1.0
 */
public interface UserAddressService {

    /**
     * 添加用户地址
     */
    int create(MallUserAddress mallUserAddress);

    /**
     * 根据id修改用户地址
     */
    int update(Long userAddressId,MallUserAddress mallUserAddress);

    /**
     * 根据id查询用户地址
     */
    MallUserAddress select(Long userAddressId);

    /**
     * 根据id删除用户地址
     */
    int delete(Long userAddressId);

    /**
     * 根据用户id查询用户地址
     */
    List<MallUserAddress> selectListByUserId(Long userId);

    /**
     * 根据id修改用户地址的默认状态
     */
    int updateDefaultFlag(Long userId,Long addressId);

    /**
     * 根据userId查询默认地址
     */
    MallUserAddress selectDefaultByUserId(Long userId);
}
