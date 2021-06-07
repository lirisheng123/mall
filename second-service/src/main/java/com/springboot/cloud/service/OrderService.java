package com.springboot.cloud.service;

import com.springboot.cloud.dto.OrderCommit;
import com.springboot.cloud.pojo.MallOrderItem;
import com.springboot.cloud.util.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: lirisheng
 * @Date: 2021/4/15 11:35
 * @Version 1.0
 */
@FeignClient(contextId ="OrderService" ,value = "order-service")
@RequestMapping("/order")
public  interface  OrderService {



    @RequestMapping(value = "/generateOrder", method = RequestMethod.POST)
    public CommonResult generateOrder(@RequestBody OrderCommit orderCommit);


    @RequestMapping(value = "/generateConfirmOrderByGoodProperty", method = RequestMethod.POST)
    public CommonResult<OrderCommit> generateConfirmOrderByGoodProperty(@RequestBody MallOrderItem goodProperty);


}
