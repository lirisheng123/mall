package com.springboot.cloud.controller;


import com.springboot.cloud.StockWithRedis.RedisLimit;
import com.springboot.cloud.StockWithRedis.StockWithRedis;
import com.springboot.cloud.dto.OrderCommit;
import com.springboot.cloud.pojo.MallSecondProperty;
import com.springboot.cloud.pojo.Stock;
import com.springboot.cloud.service.SecondService;
import com.springboot.cloud.util.CommonResult;
import com.springboot.cloud.util.ResBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

/**
 * @author : lirisheng
 * @date : 2020/9/15
 **/

@Controller
@ResponseBody
@Api(tags = "SecondController", description = "秒杀模块")
@RequestMapping("/second")
@Slf4j
public class SecondController {

    @Autowired
    SecondService secondService;


    @Autowired
    StockWithRedis stockWithRedis;

    @Autowired
    RedisLimit redisLimit;

    /**
     * 初始化 预热
     * @return
     */
    @ApiOperation(value = "初始化redis stock",notes = "预热,方便测试")
    @PostMapping("/initDBAndRedis")
    public CommonResult initRedisStock(@RequestBody MallSecondProperty mallSecondProperty){

        try {
            //初始化库存里面的商品值,库存为5000,sale =0,name=tomato;
//            stockService.initDatabaseById(1L,5000L);
            //清除订单记录
//            orderService.delOrderDBBefore();
            //重置redis里面的缓存

            //预热
            Stock stock = new Stock();
            stock.setCount(mallSecondProperty.getPropertyCount().longValue());
            stock.setSale(mallSecondProperty.getPropertySelledCount().longValue());
            stock.setId(mallSecondProperty.getGoodsPropertyId());
//            stock.setName("土豆");
            stockWithRedis.resetRedis(stock);
        }catch (Exception e){
            return CommonResult.failed("预热成功");
        }

        return CommonResult.success("预热失败");
    }

    /**
     * 关闭缓存
     * @return
     */
    @ApiOperation(value = "关闭秒杀的缓存",notes = "关闭缓存")
    @GetMapping("/deleteRedisStock")
    public CommonResult deleteRedisStock(@RequestParam("ids") List<Long> ids){

        try {
            //初始化库存里面的商品值,库存为5000,sale =0,name=tomato;
//            stockService.initDatabaseById(1L,5000L);
            //清除订单记录
//            orderService.delOrderDBBefore();
            //重置redis里面的缓存

            //预热
            Iterator iterator = ids.iterator();
            while (iterator.hasNext()){
                Stock stock = new Stock();
                stock.setCount(0L);
                stock.setSale(0L);
                stock.setId((Long) iterator.next());
                stockWithRedis.resetRedis(stock);
            }

        }catch (Exception e){
            return CommonResult.failed("关闭redis stock缓存失败");
        }

        return CommonResult.success("关闭redis stock缓存成功");
    }


    @ApiOperation(value = "统一更新所有的秒杀单信息",notes = "统一更新所有的秒杀单信息")
    @PostMapping("/updateFlashInfo")
    public CommonResult<MallSecondProperty> updateFlashInfo(@RequestBody  MallSecondProperty mallSecondProperty ){

        stockWithRedis.getSecondProperty(mallSecondProperty);

        return CommonResult.success(mallSecondProperty);
    }

    /**
     * reids+mysql来进行秒杀
     * @param sid
     * @return
     */
//    @ApiOperation(value = "使用redis缓存秒杀",notes = "无")
//    @PostMapping("/createOrderWithRedis")
//    public ResBean createOrderWithRedis(@RequestParam("sid") Long sid){
//        System.out.println("enter createOrderWithRedis");
//        try{
//           orderService.createOrderWithRedis(sid);
//
//        }catch (Exception e){
//            return ResBean.Error("订单下达失败");
//        }
//        return ResBean.OK("订单下单成功");
//    }

    /**
     * reids+mysql+限流
     * @param sid
     * @return
     */
//    @ApiOperation(value = "redis缓存+限流来进行秒杀",notes = "无")
//    @PostMapping("/createOrderWithLimitAndRedis")
//    public ResBean createOrderWithLimitAndRedis(@RequestParam("sid") Long sid){
//        //进行限流
//        //限流成功后,在进行库存的判断删减
//        try{
//            if (redisLimit.limit()) {
//                orderService.createOrderWithRedis(sid);
//            }else {
//                throw new RuntimeException();
//            }
//
//        }catch (Exception e){
//            log.error("message:"+e.getMessage());
//            return ResBean.Error("订单下达失败");
//        }
//        return ResBean.OK("订单下单成功");
//    }

    /**
     * 在提交订单的时候进行限流
     */

    /**
     * redis+mysql+异步下单
     * @param orderCommit
     * @return
     */
//    @ApiOperation(value = "redis缓存+异步下单来进行秒杀",notes = "无")
//    @PostMapping("/createOrderWithLimitAndRedisAndKafaka")
//    public CommonResult createOrderWithRedisAndKafaka(@RequestBody OrderCommit orderCommit){
//        try{
//            if (redisLimit.limit()) {
//                secondService.createOrderWithRedisAndKafaka(orderCommit);
//            }
//        }catch (Exception e){
//            return CommonResult.failed("订单下达失败");
//        }
//        return  CommonResult.success("订单下达失败");
//    }

}
