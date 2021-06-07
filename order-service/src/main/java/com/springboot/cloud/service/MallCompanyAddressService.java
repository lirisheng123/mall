package com.springboot.cloud.service;

import com.springboot.cloud.entity.MallCompanyAddress;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/23 11:40
 * @Version 1.0
 */
public interface MallCompanyAddressService {

    /**
     * 填写公司地址
     */
     int insert(MallCompanyAddress mallCompanyAddress);
    /**
     * 编辑公司地址
     */
     int update(Long id,MallCompanyAddress mallCompanyAddress);
    /**
     *分页查看公司地址
     */
    List<MallCompanyAddress> selectList(Integer pageSize,Integer pageNum);

    /**
     * 根据id查看公司地址
     */
    MallCompanyAddress select(Long id);

    /**
     * 删除公司地址
     */
    int delete(Long id);

    /**
     * type: 0:表示Send  1:表示Receive
     */
    int UpdateDefault(Long id,Integer type);

    /**
     * type: 0:表示Send  1:表示Receive
     * 获取默认状态
     */
    MallCompanyAddress selectByType(Integer type);

    /**
     * 查看公司所有的地址
     */
    List<MallCompanyAddress> selectAll();
}
