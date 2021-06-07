package com.springboot.cloud.controller;

import com.springboot.cloud.dto.MallCouponParam;
import com.springboot.cloud.entity.MallCoupon;
import com.springboot.cloud.service.MallCouponService;
import com.springboot.cloud.util.CommonPage;
import com.springboot.cloud.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/23 16:49
 * @Version 1.0
 */
@Controller
@ResponseBody
@Api(tags = "MallCouponController", description = "优惠卷管理")
@RequestMapping("/coupon")
@Slf4j
public class MallCouponController {
    @Autowired
    MallCouponService mallCouponService;

    @ApiOperation("添加优惠券")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@RequestBody  MallCoupon mallCoupon) {
        log.debug("mallCoupon:"+mallCoupon);
        int count = mallCouponService.create(mallCoupon);
        if(count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除优惠券")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult delete(@PathVariable("id") Long id) {
        int count = mallCouponService.delete(id);
        if(count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改优惠券")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable("id") Long id, @RequestBody MallCoupon mallCoupon) {
        int count = mallCouponService.update(id,mallCoupon);
        if(count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取优惠卷列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<MallCoupon>> list(
            MallCouponParam mallCouponParam,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        log.debug("coupon list:"+mallCouponParam);
        List<MallCoupon> couponList = mallCouponService.list(mallCouponParam,pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(couponList));
    }

    @ApiOperation("获取单个优惠券的详细信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<MallCoupon> getItem(@PathVariable("id") Long id) {
        MallCoupon mallCoupon = mallCouponService.getItem(id);
        return CommonResult.success(mallCoupon);
    }


}
