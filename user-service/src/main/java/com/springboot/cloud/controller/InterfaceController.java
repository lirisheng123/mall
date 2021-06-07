package com.springboot.cloud.controller;

import com.springboot.cloud.entity.MallInterface;
import com.springboot.cloud.service.MallInterfaceService;
import com.springboot.cloud.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 20:04
 * @Version 1.0
 */
@Controller
@ResponseBody
@Api(tags = "InterfaceController", description = "接口管理")
@RequestMapping("/interface")
public class InterfaceController {
    
    @Autowired
    MallInterfaceService mallInterfaceService;

    @ApiOperation("添加接口")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody MallInterface mallInterface){
        int count = mallInterfaceService.create(mallInterface);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("根据id修改接口")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult  update(@PathVariable("id") Long inteId, @RequestBody MallInterface mallInterface){
        int count = mallInterfaceService.update(inteId,mallInterface);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除接口")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult delete(@PathVariable("id") Long inteId){
        int count = mallInterfaceService.delete(inteId);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }



    @ApiOperation("根据id来查询接口")
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public CommonResult<MallInterface> selectByInterId(@PathVariable("id")Long inteId){
        return CommonResult.success(mallInterfaceService.selectByInteId(inteId));

    }

    @ApiOperation("查询所有接口")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<List<MallInterface>> selectList(){
        return CommonResult.success(mallInterfaceService.list());

    }
    
}
