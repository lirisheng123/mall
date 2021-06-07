package com.springboot.cloud.controller.productController;


import com.springboot.cloud.common.entity.MallCategoryParam;
import com.springboot.cloud.common.util.CommonResult;
import com.springboot.cloud.service.productService.MallCategoryParamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Author: lirisheng
 * @Date: 2021/2/19 9:16
 * @Version 1.0
 */

@Controller
@ResponseBody
@Api(tags = "MallCategoryParamController", description = "分类的参数管理")
@RequestMapping("/admin/categoryParam")
public class MallCategoryParamController{

@Autowired
//@Qualifier("mallCategoryParamService")
MallCategoryParamService mallCategoryParamService;


@ApiOperation("添加分类与参数的关联关系")
@RequestMapping(value = "/create", method = RequestMethod.POST)
@ResponseBody
public CommonResult create(@RequestBody MallCategoryParam mallCategoryParam){
        return mallCategoryParamService.create(mallCategoryParam);
        }


@ApiOperation("根据id选择分类与参数的关联关系")
@RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
public CommonResult<MallCategoryParam> selectByCategoryParamId(@PathVariable("id") Long goodParamId){
        return mallCategoryParamService.selectByCategoryParamId(goodParamId);
        }


@ApiOperation("根据id删除分类与参数的关联关系")
@RequestMapping(value = "/deleteItem/{id}", method = RequestMethod.GET)
public CommonResult deleteByCategoryParamId(@PathVariable("id") Long goodParamId){
        return mallCategoryParamService.deleteByCategoryParamId(goodParamId);
        }


@ApiOperation("根据id更改分类与参数的关联关系")
@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
public CommonResult updateByCategoryParamId(@PathVariable("id") Long categoryParamId,@RequestBody  MallCategoryParam mallCategoryParam){
        return mallCategoryParamService.updateByCategoryParamId(categoryParamId,mallCategoryParam);

        }


@ApiOperation("根据分类的id选择分类与参数的关联关系")
@RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
public CommonResult<List<MallCategoryParam>>selectByCategoryId(@PathVariable("id") Long categoryId){
        return mallCategoryParamService.selectByCategoryId(categoryId);
        }


@ApiOperation("根据分类的id删除分类与参数的关联关系")
@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
public CommonResult deleteByCategoryId(@PathVariable("id")  Long categoryId){
        return mallCategoryParamService.deleteByCategoryId(categoryId);
        }

}
