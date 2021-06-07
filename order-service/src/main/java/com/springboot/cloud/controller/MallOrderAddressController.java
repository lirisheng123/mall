package com.springboot.cloud.controller;

import com.springboot.cloud.entity.MallCoupon;
import com.springboot.cloud.entity.MallOrderAddress;
import com.springboot.cloud.service.MallOrderAddressService;
import com.springboot.cloud.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: lirisheng
 * @Date: 2021/4/2 17:08
 * @Version 1.0
 */
@Controller
@ResponseBody
@Api(tags = "MallOrderAddressController", description = "订单地址管理")
@RequestMapping("/orderAddress")
public class MallOrderAddressController {

    @Autowired
    MallOrderAddressService mallOrderAddressService;

    @ApiOperation("获取订单地址")
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public CommonResult<MallOrderAddress>  selectByOrderId(@PathVariable("id")  Long id){
        MallOrderAddress orderAddress = mallOrderAddressService.selectByOrderId(id);
        return CommonResult.success(orderAddress);
    }



    @ApiOperation("修改订单收货人信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateOrderAdress(@PathVariable("id") Long id, @RequestBody MallOrderAddress mallOrderAddress){
        int count = mallOrderAddressService.updateOrderAdress(id,mallOrderAddress);
        if(count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();

    }
}
