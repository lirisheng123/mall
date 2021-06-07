package com.springboot.cloud.controller;
import com.springboot.cloud.dto.CouponHistoryParam;
import com.springboot.cloud.dto.OrderAndItem;
import com.springboot.cloud.dto.OrderCommit;
import com.springboot.cloud.dto.OrderParam;
import com.springboot.cloud.entity.MallOrder;
import com.springboot.cloud.entity.MallOrderItem;
import com.springboot.cloud.service.MallOrderService;
import com.springboot.cloud.util.CommonPage;
import com.springboot.cloud.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/3/27 17:40
 * @Version 1.0
 */
@Controller
@ResponseBody
@Api(tags = "MallOrderController", description = "订单管理")
@RequestMapping("/order")
@Slf4j
public class MallOrderController {


    @Autowired
    MallOrderService mallOrderService;

    @ApiOperation("根据用户购物车信息生成确认单信息")
    @RequestMapping(value = "/generateConfirmOrderByCartIds", method = RequestMethod.GET)
    public CommonResult<OrderCommit> generateConfirmOrderByCartIds(@RequestParam("cartIds") List<Long> cartIds){
        log.debug("enter generateConfirmOrder");
        log.debug("cateIds:"+cartIds);
        return  CommonResult.success(mallOrderService.generateConfirmOrder(cartIds));
    }



    @ApiOperation("根据商品信息生成确认订单信息")
    @RequestMapping(value = "/generateConfirmOrderByGoodProperty", method = RequestMethod.POST)
    public CommonResult<OrderCommit> generateConfirmOrderByGoodProperty(@RequestBody MallOrderItem goodProperty){
        log.debug("enter generateConfirmOrderByGoodProperty");
        log.debug("goodProperty:"+goodProperty);
        return  CommonResult.success(mallOrderService.generateConfirmOrder(goodProperty));
    }


    /**
     * 根据提交的订单信息生成订单
     * @param orderCommit
     */
    @ApiOperation("根据提交信息生成订单")
    @RequestMapping(value = "/generateOrder", method = RequestMethod.POST)
    public CommonResult generateOrder(@RequestBody  OrderCommit orderCommit){
        log.debug("enter generateOrder");
        log.debug("orderCommit:"+orderCommit);
        int count = mallOrderService.generateOrder(orderCommit);
        if(count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    /**
     * 改变订单的状态,例如支付,已发货,完成,退货中,退货完成
     */
    @ApiOperation("改变订单的状态,例如支付,已发货,完成,退货中,退货完成")
    @RequestMapping(value = "/updateOrderStatus/{id}", method = RequestMethod.GET)
    public CommonResult updateOrderStatus(@PathVariable("id") Long orderId,
                                          @RequestParam("orderStatus") Integer orderStatus,
                                          @RequestParam(value = "date", required = false) Date date){
        int count = mallOrderService.updateOrderStatus(orderId,orderStatus,date);
        if(count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();

    }

    @ApiOperation("分页获取用户订单")
    @ApiImplicitParam(name = "status", value = "订单状态:0->未使用；1->已使用；2->已过期",
            allowableValues = "-1,0,1,2,3", paramType = "query", dataType = "integer")
    @RequestMapping(value = "/list/{userId}", method = RequestMethod.GET)
    public CommonResult<CommonPage<OrderAndItem>>  listByUserId(@PathVariable("userId") Long userId,
                            @RequestParam(value = "status", required = false) Integer status,
                            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        List<OrderAndItem> orderAndItems = mallOrderService.listByUserId(userId,status,pageSize,pageNum);

        return CommonResult.success(CommonPage.restPage(orderAndItems));
    }

    @ApiOperation("分页获取用户订单")
    @RequestMapping(value = "/selectList", method = RequestMethod.GET)
    public CommonResult<CommonPage<MallOrder>>  selectList(OrderParam orderParam,
                                                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        List<MallOrder> mallOrders = mallOrderService.selectList(orderParam,pageSize,pageNum);

        return CommonResult.success(CommonPage.restPage(mallOrders));
    }


    @ApiOperation("根据订单id和获取订单")
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public CommonResult<OrderAndItem> selectById(@PathVariable("id") Long id){
        return CommonResult.success(mallOrderService.selectById(id));
    }
}

