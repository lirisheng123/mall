package com.springboot.cloud.controller;

import com.springboot.cloud.dto.CouponHistoryParam;
import com.springboot.cloud.dto.MallCouponParam;
import com.springboot.cloud.entity.MallCoupon;
import com.springboot.cloud.entity.MallCouponHistory;
import com.springboot.cloud.service.MallCouponHistoryService;
import com.springboot.cloud.util.CommonPage;
import com.springboot.cloud.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: lirisheng
 * @Date: 2021/2/23 11:42
 * @Version 1.0
 */
@Controller
@ResponseBody
@Api(tags = "MallCouponHistoryController", description = "用户领取优惠卷管理")
@RequestMapping("/couponHistory")
public class MallCouponHistoryController {
    @Autowired
    MallCouponHistoryService mallCouponHistoryService;

    @ApiOperation("领取指定优惠券")
    @RequestMapping(value = "/add/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult add(@PathVariable("userId") Long userId,@RequestParam("couponId") Long couponId,
                            @RequestParam("memberNickname") String memberNickname) {
        mallCouponHistoryService.add(userId,couponId,memberNickname);
        return CommonResult.success(null,"领取成功");
    }

    @ApiOperation("获取用户优惠券历史列表")
    @ApiImplicitParam(name = "useStatus", value = "优惠券筛选类型:0->未使用；1->已使用；2->已过期",
            allowableValues = "0,1,2", paramType = "query", dataType = "integer")
    @RequestMapping(value = "/listHistory/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<CouponHistoryParam>> listHistory(@PathVariable("userId") Long userId,
                                                              @RequestParam(value = "useStatus", required = false) Integer useStatus,
                                                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<CouponHistoryParam> couponHistoryList = mallCouponHistoryService.listHistory(userId,useStatus,pageSize,pageNum);

        return CommonResult.success(CommonPage.restPage(couponHistoryList));
    }

    @ApiOperation("获取用户可用的优惠卷")
    @RequestMapping(value = "/getAvailableCoupon/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public  CommonResult<CommonPage<CouponHistoryParam>> getAvaliableCouptonByUserIdAndCount(@PathVariable("userId") Long userId,
                                                                        @RequestParam("count") String count,
                                                                        @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                                        @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum) {

        List<CouponHistoryParam> couponHistoryParams = mallCouponHistoryService.getAvaliableCouptonByUserIdAndCount(userId,count,pageSize,pageNum);

        return  CommonResult.success(CommonPage.restPage(couponHistoryParams));

    }


//    @ApiOperation("获取用户优惠券历史列表")
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<CommonPage<MallCoupon>> list(
//            MallCouponParam mallCouponParam,
//            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
//            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
//        List<MallCoupon> couponList = mallCouponService.list(mallCouponParam,pageSize,pageNum);
//        return CommonResult.success(CommonPage.restPage(couponList));
//    }

    @ApiOperation("获取用户优惠券列表")
    @ApiImplicitParam(name = "useStatus", value = "优惠券筛选类型:0->未使用；1->已使用；2->已过期",
            allowableValues = "0,1,2", paramType = "query", dataType = "integer")
    @RequestMapping(value = "/list/{useId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<MallCoupon>> list(@PathVariable Long userId, @RequestParam(value = "useStatus", required = false) Integer useStatus) {
        List<MallCoupon> couponList = mallCouponHistoryService.list(userId,useStatus);
        return CommonResult.success(couponList);
    }

}
