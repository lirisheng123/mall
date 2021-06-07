package com.springboot.cloud.controller;

import com.springboot.cloud.entity.MallHomeAdvertise;
import com.springboot.cloud.entity.MallSecondProperty;
import com.springboot.cloud.service.MallSecondPropertyService;
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
 * @Date: 2021/4/25 17:04
 * @Version 1.0
 */
@Controller
@ResponseBody
@Api(tags = "MallSecondPropertyController", description = "秒杀属性管理")
@RequestMapping("/secondProperty")
@Slf4j
public class MallSecondPropertyController {

    @Autowired
    MallSecondPropertyService mallSecondPropertyService;


    @ApiOperation(value = "批量进行修改")
    @RequestMapping(value = "/updateList", method = RequestMethod.POST)
    public CommonResult updateList(@RequestBody  List<MallSecondProperty> mallSecondProperties){
        CommonResult commonResult;
        int count = mallSecondPropertyService.updateList(mallSecondProperties);
        if (count == 1) {
            commonResult = CommonResult.success(count);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }


    @ApiOperation(value = "根据秒杀id获取秒杀属性")
    @RequestMapping(value = "/selectByFlshId/{flashId}", method = RequestMethod.GET)
    public CommonResult<List<MallSecondProperty>>  selectByFlashId(@PathVariable("flashId") Long flashId){
        return  CommonResult.success(mallSecondPropertyService.selectByFlashId(flashId));
    }
}
