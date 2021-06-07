package com.springboot.cloud.controller;

import com.springboot.cloud.dto.CouponHistoryParam;
import com.springboot.cloud.entity.MallOrderItem;
import com.springboot.cloud.service.MallOrderItemService;
import com.springboot.cloud.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/4/3 15:44
 * @Version 1.0
 */
@Controller
@ResponseBody
@Api(tags = "MallOrderItemController", description = "订单详情管理")
@RequestMapping("/orderItem")
@Slf4j
public class MallOrderItemController {

    @Autowired
    MallOrderItemService mallOrderItemService;

    /**
     * 根据订单ID获取订单详情
     */
    @ApiOperation("根据订单ID获取订单详情")
    @RequestMapping(value = "/selectByOrderId/{orderId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<MallOrderItem>> selectByOrderId(@PathVariable("orderId")Long orderId){
        List<MallOrderItem> mallOrderItems = mallOrderItemService.selectByOrderId(orderId);

        return  CommonResult.success(mallOrderItems);
    }
}
