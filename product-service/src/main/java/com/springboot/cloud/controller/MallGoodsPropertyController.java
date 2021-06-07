package com.springboot.cloud.controller;

import com.springboot.cloud.entity.MallGoodsProperty;
import com.springboot.cloud.service.MallGoodsPropertyService;
import com.springboot.cloud.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/19 20:35
 * @Version 1.0
 */
@Controller
@ResponseBody
@Api(tags = "MallGoodsPropertyController", description = "商品的属性管理")
@RequestMapping("/goodsProperty")
public class MallGoodsPropertyController {

    @Autowired
    MallGoodsPropertyService mallGoodsPropertyService;


    @ApiOperation("添加分类与参数的关联关系添加商品属性")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody  MallGoodsProperty mallGoodsProperty){
        int count  =mallGoodsPropertyService.create(mallGoodsProperty);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("根据id修改商品属性")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id, @RequestBody  MallGoodsProperty mallGoodsProperty){
        int count  =mallGoodsPropertyService.update(id,mallGoodsProperty);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("获取id获取单个商品属性信息")
    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    public CommonResult<MallGoodsProperty>  getItem(@PathVariable Long id){
        return CommonResult.success(mallGoodsPropertyService.getItem(id));

    }



    @ApiOperation("根据id删除商品属性")
    @RequestMapping(value = "/deleteItem/{id}", method = RequestMethod.GET)
    public CommonResult delete( @PathVariable Long id){
        int count  =mallGoodsPropertyService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("根据商品id查找商品属性")
    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    public CommonResult<List<MallGoodsProperty>> selectByGoodsId(@PathVariable("id")Long goodsId){
        return CommonResult.success(mallGoodsPropertyService.selectByGoodsId(goodsId));
    }


    @ApiOperation("批量修改商品的属性")
    @RequestMapping(value = "/updateList", method = RequestMethod.POST)
    public CommonResult updateList(@RequestBody  List<MallGoodsProperty> mallGoodsProperties){
        int count  =mallGoodsPropertyService.updateList(mallGoodsProperties);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("根据商品属性id来修改相应的库存呢")
    @RequestMapping(value = "/updateCount/{id}", method = RequestMethod.GET)
    public CommonResult updateSkuById(@PathVariable("id") Long id,@RequestParam("count") Integer count){
        int count1  =mallGoodsPropertyService.updateSkuById(id,count);
        if (count1 > 0) {
            return CommonResult.success(count1);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("根据id来减少商品属性的库存")
    @RequestMapping(value = "/decreaseSkuById/{id}", method = RequestMethod.GET)
    public CommonResult  decreaseSkuById(@PathVariable("id")Long id,@RequestParam("count") Integer count){
        int count1  =mallGoodsPropertyService.decreaseSkuById(id,count);
        if (count1 > 0) {
            return CommonResult.success(count1);
        } else {
            return CommonResult.failed();
        }
    }



}
