package com.springboot.cloud.service.impl;

import com.github.pagehelper.PageHelper;
import com.springboot.cloud.dao.MallCouponHistoryMapper;
import com.springboot.cloud.dto.CouponHistoryParam;
import com.springboot.cloud.entity.MallCoupon;
import com.springboot.cloud.entity.MallCouponHistory;
import com.springboot.cloud.exception.Asserts;
import com.springboot.cloud.service.MallCouponHistoryService;
import com.springboot.cloud.service.MallCouponService;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: lirisheng
 * @Date: 2021/2/23 11:42
 * @Version 1.0
 */
@Service
public class MallCouponHistoryServiceImpl implements MallCouponHistoryService {
    @Autowired
    MallCouponService mallCouponService;

    @Autowired
    MallCouponHistoryMapper mallCouponHistoryMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Long userId, Long couponId,String memberNickname) {

        //获取优惠券信息，判断数量
        MallCoupon coupon = mallCouponService.getItem(couponId);
        if (coupon == null) {
            Asserts.fail("优惠券不存在");
        }
        if (coupon.getCount() <= 0) {
            Asserts.fail("优惠券已经领完了");
        }

//        Date now = new Date();
//        if (now.before(coupon.getEnableTime())) {
//            Asserts.fail("优惠券还没到领取时间");
//        }
        //判断用户领取的优惠券数量是否超过限制
        long count = mallCouponHistoryMapper.countCouponByUserId(userId, couponId);
        if (count >= coupon.getPerLimit()) {
            Asserts.fail("您已经领取过该优惠券");
        }
        //生成领取优惠券历史
        MallCouponHistory couponHistory = new MallCouponHistory();
        couponHistory.setCouponId(couponId);
        couponHistory.setUserId(userId);
        couponHistory.setMemberNickname(memberNickname);
//        couponHistory.setMemberNickname(userId + "别名");
        couponHistory.setUseStatus(0);
        //未使用
//        couponHistory.setUseStatus(0);
        mallCouponHistoryMapper.insert(couponHistory);
        //修改优惠券表的数量、领取数量
        coupon.setCount(coupon.getCount() - 1);
        coupon.setReceiveCount(coupon.getReceiveCount() == null ? 1 : coupon.getReceiveCount() + 1);
        mallCouponService.updateCountAndReciveCountByPrimaryKey(coupon);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<CouponHistoryParam>  listHistory(Long userId, Integer useStatus, Integer pagesize, Integer pageNum) {
        PageHelper.startPage(pageNum,pagesize);
        List<CouponHistoryParam> couponHistoryParams = new ArrayList<>();


        Iterator<MallCouponHistory> iterator = mallCouponHistoryMapper.selectUseStatusByPrimaryId(userId, useStatus).iterator();
        while(iterator.hasNext()){
            MallCouponHistory mallCouponHistory = iterator.next();
            CouponHistoryParam couponHistoryParam = new CouponHistoryParam();
            MallCoupon mallCoupon = mallCouponService.getItem(mallCouponHistory.getCouponId());
            if(useStatus==0){
                //判断是否过期
                Date now = new Date();
                if (mallCoupon.getEndTime().before(now)) {
                    //过期
                    mallCouponHistory.setUseStatus(2);
                    //更新history的值
                    mallCouponHistoryMapper.updateByPrimaryKey(mallCouponHistory);
                    continue;
                 }else {
                    changeCouponHistoryToParam(couponHistoryParam,mallCouponHistory);
                    changeCouponToParam(couponHistoryParam,mallCoupon);
                }

            }else{
                changeCouponHistoryToParam(couponHistoryParam,mallCouponHistory);
                changeCouponToParam(couponHistoryParam,mallCoupon);
            }

            couponHistoryParams.add(couponHistoryParam);
        }
        return  couponHistoryParams;
    }
    void changeCouponHistoryToParam(CouponHistoryParam couponHistoryParam,MallCouponHistory mallCouponHistory){


        couponHistoryParam.setCouponHistoryId(mallCouponHistory.getCouponHistoryId());
        couponHistoryParam.setUseStatus(mallCouponHistory.getUseStatus());
    }
    void changeCouponToParam(CouponHistoryParam couponHistoryParam,MallCoupon mallCoupon){


        couponHistoryParam.setName(mallCoupon.getName());
        couponHistoryParam.setCount(mallCoupon.getCount());
        couponHistoryParam.setEndTime(mallCoupon.getEndTime());
        couponHistoryParam.setStartTime(mallCoupon.getStartTime());
        couponHistoryParam.setNote(mallCoupon.getNote());
        couponHistoryParam.setAmount(mallCoupon.getAmount());
        couponHistoryParam.setMinPoint(mallCoupon.getMinPoint());
        couponHistoryParam.setPerLimit(mallCoupon.getPerLimit());
        couponHistoryParam.setUseCount(mallCoupon.getUseCount());
        couponHistoryParam.setReceiveCount(mallCoupon.getReceiveCount());

    }


    @Override
    public List<MallCoupon> list(Long userId, Integer useStatus) {

        return mallCouponHistoryMapper.getCouponList(userId, useStatus);
    }

    @Override
    public List<CouponHistoryParam> getAvaliableCouptonByUserIdAndCount(Long userId, String count,
                                                                        Integer pageSize,Integer pageNum) {

        List<CouponHistoryParam> couponHistoryParams = listHistory(userId,0,pageSize,pageNum);
        BigDecimal count1 = new BigDecimal(count);
        Date now = new Date();
        couponHistoryParams=couponHistoryParams.stream().filter(couponHistoryParam ->{
            if(couponHistoryParam.getMinPoint().compareTo(count1)<=0&& couponHistoryParam.getStartTime().before(now)){
                return true;
            }
            return  false;
        }
        ).collect(Collectors.toList());

        return couponHistoryParams;

    }

    @Override
    public MallCouponHistory select(Long couponHistoryId) {
        return mallCouponHistoryMapper.selectByPrimaryKey(couponHistoryId);
    }

    @Override
    public int update(Long couponHistoryId, MallCouponHistory mallCouponHistory) {
         mallCouponHistory.setCouponHistoryId(couponHistoryId);
        return mallCouponHistoryMapper.updateByPrimaryKey(mallCouponHistory);
    }
}
