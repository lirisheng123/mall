package com.springboot.cloud.controller.productController;


import com.springboot.cloud.common.entity.MallCategoryGrand;
import com.springboot.cloud.common.util.CommonResult;
import com.springboot.cloud.service.productService.MallCategoryGrandService;

import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/19 9:17
 * @Version 1.0
 */

@Controller
@ResponseBody
@Api(tags = "MallCategoryGrandController", description = "分类与品牌关联关系管理")
@RequestMapping("/admin/categoryGrand")
public class MallCategoryGrandController {


      @Autowired
//    @Qualifier("mallCategoryGrandService")
//     @Resource(name = "mallCategoryGrandService")
    MallCategoryGrandService mallCategoryGrandService;


    @ApiOperation("删除某个分类的所有的品牌关联关系")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult deleteCategoryGrand(@PathVariable("id") Long categoryId){
      return  mallCategoryGrandService.deleteCategoryGrand(categoryId);
    }

    @ApiOperation("查询某个分类的所有的品牌")
    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    public CommonResult<String> listByCategoryId(@PathVariable("id") Long categoryId){

        return mallCategoryGrandService.listByCategoryId(categoryId);


    }


    @ApiOperation(" 更改某个分类的所有的品牌")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult updateByCategoryId(@PathVariable("id") Long categoryId,@RequestBody  List<MallCategoryGrand> mallCategoryGrand){
      return  mallCategoryGrandService.updateByCategoryId(categoryId,mallCategoryGrand);
    }
}
