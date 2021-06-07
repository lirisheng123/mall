package com.springboot.cloud.controller;

import com.springboot.cloud.dto.GrandAndCateGrandId;
import com.springboot.cloud.entity.MallCategoryGrand;
import com.springboot.cloud.entity.MallGrand;
import com.springboot.cloud.service.MallCategoryGrandService;
import com.springboot.cloud.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/19 9:17
 * @Version 1.0
 */
@Controller
@ResponseBody
@Api(tags = "MallCategoryGrandController", description = "分类与品牌关联关系管理")
@RequestMapping("/categoryGrand")
public class MallCategoryGrandController {

    @Autowired
    MallCategoryGrandService mallCategoryGrandService;


    /**
     * 增加品牌和分类关联关系
     * @return
     */

    @ApiOperation("添加品牌和分类之间的关系")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody MallCategoryGrand mallCategoryGrand){
        mallCategoryGrand.setUpdateUser(1);
        mallCategoryGrand.setCreateUser(1);
        int count = mallCategoryGrandService.create(mallCategoryGrand);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除某个分类的所有的品牌关联关系")
    @RequestMapping(value = "/deleteAll/{id}", method = RequestMethod.GET)
    public CommonResult deleteCategoryGrand(@PathVariable("id") Long categoryId){
        int count = mallCategoryGrandService.deleteByCategoryId(categoryId);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("查询某个分类的所有的品牌")
    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    public CommonResult<List<GrandAndCateGrandId>> listByCategoryId(@PathVariable("id") Long categoryId){
        List<GrandAndCateGrandId> grands=mallCategoryGrandService.selectByCategoryId(categoryId);
        return CommonResult.success(grands);


    }

    @ApiOperation("删除某个分类的某个品牌")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult deleteById(@PathVariable("id") Long id){
        int count =mallCategoryGrandService.deleteById(id);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }




    @ApiOperation(" 更改某个分类的所有的品牌")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult updateByCategoryId(@PathVariable("id") Long categoryId,@RequestBody  List<MallCategoryGrand> mallCategoryGrand){
        int count = mallCategoryGrandService.updateByCategoryId(categoryId,mallCategoryGrand);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }
}
