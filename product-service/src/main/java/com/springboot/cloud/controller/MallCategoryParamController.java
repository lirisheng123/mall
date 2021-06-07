package com.springboot.cloud.controller;

import com.springboot.cloud.entity.MallCategoryParam;
import com.springboot.cloud.service.MallCategoryParamService;
import com.springboot.cloud.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/categoryParam")
public class MallCategoryParamController {

    @Autowired
    MallCategoryParamService mallCategoryParamService;



    @ApiOperation("添加分类与参数的关联关系")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody MallCategoryParam mallCategoryParam){
        mallCategoryParam.setUpdateUser(1);
        mallCategoryParam.setCreateUser(1);
        int count = mallCategoryParamService.create(mallCategoryParam);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("根据id选择分类与参数的关联关系")
    @RequestMapping(value = "/item/{cid}", method = RequestMethod.GET)
    public CommonResult<MallCategoryParam> selectByCategoryParamId(@PathVariable("cid") Long goodParamId){
        return CommonResult.success(mallCategoryParamService.selectByCategoryParamId(goodParamId));
    }

    @ApiOperation("根据id删除分类与参数的关联关系")
    @RequestMapping(value = "/deleteItem/{id}", method = RequestMethod.GET)
    public CommonResult deleteByCategoryParamId(@PathVariable("id") Long goodParamId){
        int count = mallCategoryParamService.deleteByCategoryParamId(goodParamId);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }




    @ApiOperation("根据id更改分类与参数的关联关系")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult  updateByCategoryParamId(@PathVariable("id") Long categoryParamId,@RequestBody  MallCategoryParam mallCategoryParam){

        int count = mallCategoryParamService.updateByCategoryParamId(categoryParamId,mallCategoryParam);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }


    @ApiOperation("根据分类的id选择分类与参数的关联关系")
    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    public CommonResult<List<MallCategoryParam>> selectByCategoryId(@PathVariable("id") Long categoryId ){
        return CommonResult.success(mallCategoryParamService.selectByCategoryId(categoryId));
    }


    @ApiOperation("根据分类的id删除分类与参数的关联关系")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult deleteByCategoryId(@PathVariable("id")  Long categoryId ){
        int count = mallCategoryParamService.deleteByCategoryId(categoryId);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

}
