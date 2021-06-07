package com.springboot.cloud.controller;

import com.springboot.cloud.dto.ReturnApplyParams;
import com.springboot.cloud.entity.MallOrderReturnApply;
import com.springboot.cloud.service.MallOrderReturnApplyService;
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
 * @Date: 2021/4/2 18:21
 * @Version 1.0
 */
@Controller
@ResponseBody
@Api(tags = "MallOrderReturnApplyController", description = "退货单")
@RequestMapping("/orderReturnApply")
@Slf4j
public class MallOrderReturnApplyController {

    @Autowired
    MallOrderReturnApplyService mallOrderReturnApplyService;


    @ApiOperation("添加退货单")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult insert(@RequestBody MallOrderReturnApply mallOrderReturnApply){
        int count = mallOrderReturnApplyService.insert(mallOrderReturnApply);
        if(count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @ApiOperation("根据order查看退货单")
    @RequestMapping(value = "/selectOrder/{orderId}", method = RequestMethod.GET)
    public CommonResult<MallOrderReturnApply>  selectByOrderId(@PathVariable("orderId")Long orderId){
        MallOrderReturnApply mallOrderReturnApply = mallOrderReturnApplyService.selectByOrderId(orderId);
        return CommonResult.success(mallOrderReturnApply);
    }


    @ApiOperation("进行退货单的状态的更改")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult upadateStatusById(@PathVariable("id") Long id, @RequestBody MallOrderReturnApply mallOrderReturnApply){

        int count = mallOrderReturnApplyService.upadateStatusById(id,mallOrderReturnApply);
        if(count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @ApiOperation("id查看退货单")
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public CommonResult<MallOrderReturnApply>  selectById(@PathVariable("id") Long id){
        MallOrderReturnApply mallOrderReturnApply = mallOrderReturnApplyService.selectById(id);
        return CommonResult.success(mallOrderReturnApply);
    }


    @ApiOperation("多参数分页查询退货单")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<MallOrderReturnApply>> selectList(ReturnApplyParams returnApplyParams ,
                                                                     @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                                     @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum){
        List<MallOrderReturnApply> mallOrderReturnApplies = mallOrderReturnApplyService.selectList(returnApplyParams,pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(mallOrderReturnApplies));
    }
}
