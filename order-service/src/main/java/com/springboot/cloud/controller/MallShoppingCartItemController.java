package com.springboot.cloud.controller;

import com.springboot.cloud.entity.MallShoppingCartItem;
import com.springboot.cloud.service.MallShoppingCartItemService;
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
 * @Date: 2021/2/24 21:40
 * @Version 1.0
 */
@Controller
@ResponseBody
@Api(tags = "MallShoppingCartItemController", description = "购物车管理")
@RequestMapping("/shoppingCartItem")
@Slf4j
public class MallShoppingCartItemController {
    @Autowired
    MallShoppingCartItemService mallShoppingCartItemService;


    @ApiOperation("添加商品到购物车")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@RequestBody MallShoppingCartItem cartItem) {
        log.debug("cartItem:"+cartItem);
        int count = mallShoppingCartItemService.add(cartItem);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取某个会员的购物车列表")
    @RequestMapping(value = "/list/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<MallShoppingCartItem>> list(@PathVariable("userId")Long userId) {
        List<MallShoppingCartItem> cartItemList = mallShoppingCartItemService.list(userId);
        return CommonResult.success(cartItemList);
    }


    @ApiOperation("修改购物车中某个商品的数量")
    @RequestMapping(value = "/update/quantity/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult updateQuantity(@PathVariable Long id,
                                       @RequestParam Integer quantity) {
        int count = mallShoppingCartItemService.updateQuantity(id, quantity);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }



//    @ApiOperation("修改购物车中商品的规格")
//    @RequestMapping(value = "/update/attr/{id}", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult updateAttr(@PathVariable("id")Long id,@RequestBody MallShoppingCartItem cartItem) {
//        int count = mallShoppingCartItemService.updateAttr(id,cartItem);
//        if (count > 0) {
//            return CommonResult.success(count);
//        }
//        return CommonResult.failed();
//    }

    @ApiOperation("删除购物车中的某个商品")
    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable("userId")Long userId,@RequestBody List<Long> ids) {
        int count = mallShoppingCartItemService.delete(userId, ids);

        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("清空购物车")
    @RequestMapping(value = "/clear/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult clear(@PathVariable("userId")Long userId) {
        int count = mallShoppingCartItemService.clear(userId);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

}
