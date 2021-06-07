package com.springboot.cloud.controller;

import com.springboot.cloud.dto.AdvertiesParams;
import com.springboot.cloud.dto.SecondParams;
import com.springboot.cloud.entity.MallHomeAdvertise;
import com.springboot.cloud.service.MallHomeAdvertiseService;
import com.springboot.cloud.util.CommonPage;
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
 * @Date: 2021/3/30 1:11
 * @Version 1.0
 */
@Controller
@ResponseBody
@Api(tags = "MallHomeAdvertiseController", description = "广告管理")
@RequestMapping("/homeAdvertise")
@Slf4j
public class MallHomeAdvertiseController {

    @Autowired
    MallHomeAdvertiseService mallHomeAdvertiseService;


    @ApiOperation(value = "添加")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult insert(@RequestBody MallHomeAdvertise mallHomeAdvertise){
        int count = mallHomeAdvertiseService.insert(mallHomeAdvertise);
        if (count == 1) {
            return CommonResult.success(count);
        } else {
            return  CommonResult.failed();
        }
    }


    @ApiOperation(value = "多个参数分页查看")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public CommonResult<CommonPage<MallHomeAdvertise>>   selectList(AdvertiesParams advertiesParams ,
                                                                    @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        List<MallHomeAdvertise> mallHomeAdvertises = mallHomeAdvertiseService.selectList(advertiesParams,pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(mallHomeAdvertises));
    }

    @ApiOperation(value = "批量删除")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.GET)
    public CommonResult deleteList(@RequestParam("ids") List<Long> ids){
        log.debug("ids:"+ids);
        int count = mallHomeAdvertiseService.deleteList(ids);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }

    @ApiOperation(value = "更新")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable("id") Long id, @RequestBody MallHomeAdvertise mallHomeAdvertise){
        CommonResult commonResult;
        int count = mallHomeAdvertiseService.update(id,mallHomeAdvertise);
        if (count == 1) {
            commonResult = CommonResult.success(count);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

    @ApiOperation(value = "根据id查询")
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public CommonResult<MallHomeAdvertise>  select(@PathVariable("id")Long id){
        return CommonResult.success(mallHomeAdvertiseService.select(id));
    }


    @ApiOperation(value = "根据id来更新状态")
    @RequestMapping(value = "/updateStatueById/{id}", method = RequestMethod.GET)
    public CommonResult updateStatueById(@PathVariable("id")Long id,@RequestParam("status") Integer status){
        int count = mallHomeAdvertiseService.updateStatueById(id,status);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }
}
