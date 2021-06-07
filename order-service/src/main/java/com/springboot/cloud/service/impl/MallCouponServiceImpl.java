package com.springboot.cloud.service.impl;

import com.github.pagehelper.PageHelper;
import com.springboot.cloud.dao.MallCouponMapper;
import com.springboot.cloud.dto.MallCouponParam;
import com.springboot.cloud.entity.MallCoupon;
import com.springboot.cloud.service.MallCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/23 11:41
 * @Version 1.0
 */
@Service
public class MallCouponServiceImpl implements MallCouponService {

   @Autowired
    MallCouponMapper mallCouponMapper;

    @Override
    public int create(MallCoupon mallCoupon) {
       
        //插入优惠券表
        int count = mallCouponMapper.insert(mallCoupon);
        return count;
    }

    @Override
    public int delete(Long id) {
        //删除优惠券
        int count = mallCouponMapper.deleteByPrimaryKey(id);
        return count;
    }

    

    @Override
    public int update(Long id, MallCoupon mallCoupon) {
        mallCoupon.setMallCouponId(id);
        int count =mallCouponMapper.updateByPrimaryKey(mallCoupon);
        return count;
    }

    @Override
    public List<MallCoupon> list(MallCouponParam mallCouponParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        return mallCouponMapper.selectByMulParam(mallCouponParam);
    }

    @Override
    public MallCoupon getItem(Long id) {
        return mallCouponMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateCountAndReciveCountByPrimaryKey(MallCoupon mallCoupon){
        return  mallCouponMapper.updateCountAndReciveCountByPrimaryKey(mallCoupon);
    }

    @Override
    public int updateUserCount(Long id) {
        return mallCouponMapper.updateUserCount(id);
    }
}
