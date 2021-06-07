package com.springboot.cloud.controller;

import com.springboot.cloud.entity.MallCategoryParam;
import com.springboot.cloud.entity.MallCategoryProperty;
import com.springboot.cloud.service.MallCategoryPropertyService;
import com.springboot.cloud.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/3/20 15:29
 * @Version 1.0
 */
@Controller
@ResponseBody
@Api(tags = "MallCategoryPropertyController", description = "分类的属性管理")
@RequestMapping("/categoryProperty")
public class MallCategoryPropertyController {

    @Autowired
    MallCategoryPropertyService mallCategoryPropertyService;

    @ApiOperation("添加分类的属性")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult add(@RequestBody  MallCategoryProperty mallCategoryProperty){

        int count = mallCategoryPropertyService.add(mallCategoryProperty);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("查看某个分类的所有的属性")
    @RequestMapping(value = "/list/{cid}", method = RequestMethod.GET)
    public  CommonResult<List<MallCategoryProperty>> selectByCateId(@PathVariable("cid") Long id){
        return  CommonResult.success(mallCategoryPropertyService.selectByCateId(id));
    }


    @ApiOperation("根据id删除")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult  deleteById(@PathVariable("id") Long id){
        int count = mallCategoryPropertyService.deleteById(id);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }
}
