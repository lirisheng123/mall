package com.springboot.cloud.controller;

import com.springboot.cloud.entity.MallCoupon;
import com.springboot.cloud.entity.MallDeliveryLogistics;
import com.springboot.cloud.service.MallDeliveryLogisticsService;
import com.springboot.cloud.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: lirisheng
 * @Date: 2021/4/2 17:18
 * @Version 1.0
 */
@Controller
@ResponseBody
@Api(tags = "MallDeliveryLogisticsController", description = "物流管理")
@RequestMapping("/deliveryLogistics")
@Slf4j
public class MallDeliveryLogisticsController {

    @Autowired
    MallDeliveryLogisticsService  mallDeliveryLogisticsService;


    @ApiOperation("填写物流单号")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult insert(@RequestBody MallDeliveryLogistics mallDeliveryLogistics){
        int count = mallDeliveryLogisticsService.insert(mallDeliveryLogistics);
        if(count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("根据orderId物流单号")
    @RequestMapping(value = "/selectd/{orderId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<MallDeliveryLogistics>   selectByOrderId(@PathVariable("orderId")Long orderId){
        MallDeliveryLogistics mallDeliveryLogistics = mallDeliveryLogisticsService.selectByOrderId(orderId);
        return CommonResult.success(mallDeliveryLogistics);
    }
}
