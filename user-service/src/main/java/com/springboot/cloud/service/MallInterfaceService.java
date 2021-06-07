package com.springboot.cloud.service;

import com.springboot.cloud.entity.MallInterface;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 23:08
 * @Version 1.0
 */
public interface MallInterfaceService {

    /**
     * 添加接口
     */
    int create(MallInterface mallInterface) ;

    /**
     * 根据id修改接口
     */
    int update(Long inteId,MallInterface mallInterface);
    /**
     * 删除接口
     */
    int  delete(Long inteId);

    /**
     * 根据id来查询接口
     */
    MallInterface  selectByInteId(Long inteId);

    /**
     * 查询所有接口
     */
    List<MallInterface> list();

    /**
     * 根据接口的名字查询接口
     */
    MallInterface selectByInteName(String inteName);

    List<MallInterface> selectAll();
}
