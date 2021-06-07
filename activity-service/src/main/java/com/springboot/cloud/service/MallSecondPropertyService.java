package com.springboot.cloud.service;

import com.springboot.cloud.entity.MallSecondProperty;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/4/25 15:55
 * @Version 1.0
 */
public interface MallSecondPropertyService {

    /**
     * 添加
     */
    int  insert(MallSecondProperty mallSecondProperty);

    /**
     * 修改
     */
    int update(Long id,MallSecondProperty mallSecondProperty);

    /**
     * 批量进行修改
     */
    int updateList(List<MallSecondProperty> mallSecondProperties);

    /**
     * 根据秒杀id获取秒杀属性
     */
    List<MallSecondProperty> selectByFlashId(Long flashId);

    /**
     * 批量删除
     */
    int deleteList(List<Long > ids);
}
