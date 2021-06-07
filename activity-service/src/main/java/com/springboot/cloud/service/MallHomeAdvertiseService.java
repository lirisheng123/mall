package com.springboot.cloud.service;

import com.springboot.cloud.dto.AdvertiesParams;
import com.springboot.cloud.dto.SecondParams;
import com.springboot.cloud.entity.MallHomeAdvertise;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/3/30 0:56
 * @Version 1.0
 */
public interface MallHomeAdvertiseService {

    /**
     * 添加
     */
    int insert( MallHomeAdvertise mallHomeAdvertise);

    /**
     * 多个参数分页查看
     */
    List<MallHomeAdvertise>  selectList(AdvertiesParams advertiesParams ,Integer pageSize,Integer pageNum);
    /**
     * 批量删除
     */
    int deleteList(List<Long> ids);
    /**
     * 更改
     */
    int update(Long id,MallHomeAdvertise mallHomeAdvertise);
    /**
     * 根据id查询
     */
    MallHomeAdvertise select(Long id);

    /**
     * 根据id来更新状态
     */
    int updateStatueById(Long id,Integer status);
}
