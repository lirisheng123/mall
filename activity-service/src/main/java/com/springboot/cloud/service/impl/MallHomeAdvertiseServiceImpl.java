package com.springboot.cloud.service.impl;

import com.github.pagehelper.PageHelper;
import com.springboot.cloud.dao.MallHomeAdvertiseMapper;
import com.springboot.cloud.dto.AdvertiesParams;
import com.springboot.cloud.dto.SecondParams;
import com.springboot.cloud.entity.MallHomeAdvertise;
import com.springboot.cloud.service.MallHomeAdvertiseService;
import com.springboot.cloud.service.MallSecondPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: lirisheng
 * @Date: 2021/3/30 0:57
 * @Version 1.0
 */
@Service
public class MallHomeAdvertiseServiceImpl implements MallHomeAdvertiseService{

    @Autowired
    MallHomeAdvertiseMapper mallHomeAdvertiseMapper;


    @Override
    public int insert( MallHomeAdvertise mallHomeAdvertise) {

        return mallHomeAdvertiseMapper.insert(mallHomeAdvertise);
    }

    @Override
    public  List<MallHomeAdvertise> selectList(AdvertiesParams advertiesParams, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        return  mallHomeAdvertiseMapper.selectList(advertiesParams);

    }

    @Override
    public int deleteList(List<Long> ids) {
        Map<String,Object> map = new HashMap<>();
        map.put("list",ids);
        return mallHomeAdvertiseMapper.deleteList(ids);
    }

    @Override
    public int update(Long id,MallHomeAdvertise mallHomeAdvertise) {
        return mallHomeAdvertiseMapper.updateByPrimaryKey(mallHomeAdvertise);
    }

    @Override
    public MallHomeAdvertise select(Long id) {
        return mallHomeAdvertiseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateStatueById(Long id, Integer status) {
        return mallHomeAdvertiseMapper.updateStatueById(id,status);
    }
}
