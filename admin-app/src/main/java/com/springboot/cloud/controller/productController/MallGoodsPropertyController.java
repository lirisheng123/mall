package com.springboot.cloud.controller.productController;


import com.springboot.cloud.common.entity.MallGoodsProperty;
import com.springboot.cloud.common.util.CommonResult;
import com.springboot.cloud.service.productService.MallGoodsPropertyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@RequestMapping("/admin/goodsProperty")
public class MallGoodsPropertyController {

    @Autowired
//    @Qualifier("mallGoodsPropertyService")
    MallGoodsPropertyService mallGoodsPropertyService;


    @ApiOperation("添加分类与参数的关联关系添加商品属性")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody MallGoodsProperty mallGoodsProperty){
       return  mallGoodsPropertyService.create(mallGoodsProperty);
    }


    @ApiOperation("根据id修改商品属性")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id, @RequestBody  MallGoodsProperty mallGoodsProperty){
        return  mallGoodsPropertyService.update(id,mallGoodsProperty);

    }


    @ApiOperation("获取id获取单个商品属性信息")
    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    public CommonResult<MallGoodsProperty>  getItem(@PathVariable Long id){
       return  mallGoodsPropertyService.getItem(id);

    }



    @ApiOperation("根据id删除商品属性")
    @RequestMapping(value = "/deleteItem/{id}", method = RequestMethod.GET)
    public CommonResult delete( @PathVariable Long id){
      return  mallGoodsPropertyService.delete(id);
    }


    @ApiOperation("根据商品id查找商品属性")
    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    public CommonResult<List<MallGoodsProperty>> selectByGoodsId(@PathVariable("id")Long goodsId){
       return  mallGoodsPropertyService.selectByGoodsId(goodsId);
    }
}
