package com.springboot.cloud.controller;

import com.springboot.cloud.dto.FlashPromotionParams;
import com.springboot.cloud.dto.SecondParams;
import com.springboot.cloud.entity.MallFlashPromotionSession;
import com.springboot.cloud.entity.MallHomeAdvertise;
import com.springboot.cloud.service.MallFlashPromotionSessionService;
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
@Api(tags = "MallFlashPromotionSessionController", description = "秒杀单模块")
@RequestMapping("/flashPromotion")
@Slf4j
public class MallFlashPromotionSessionController {

    @Autowired
    MallFlashPromotionSessionService mall;

    @ApiOperation(value = "添加秒杀单")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody SecondParams secondParams){
        int count = mall.create(secondParams);
        if (count == 1) {
            return CommonResult.success(count);
        } else {
            return  CommonResult.failed();
        }
    }

    @ApiOperation(value = "批量删除秒杀单")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.GET)
    public CommonResult  deleteList(@RequestParam("ids")List<Long> ids){
        log.debug("ids:"+ids);
        int count = mall.deleteList(ids);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }

    @ApiOperation(value = "更改秒杀单")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable("id")Long id,@RequestBody MallFlashPromotionSession mallFlashPromotionSession){
        CommonResult commonResult;
        int count = mall.update(id,mallFlashPromotionSession);
        if (count == 1) {
            commonResult = CommonResult.success(count);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

    @ApiOperation(value = "多参数分页查询秒杀单")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public CommonResult<CommonPage<MallFlashPromotionSession>>  selectList(FlashPromotionParams flashPromotionParams,
                                                                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        List<MallFlashPromotionSession> mallFlashPromotionSessions = mall.selectList(flashPromotionParams,pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(mallFlashPromotionSessions));

    }

    @ApiOperation(value = "根据id来更新状态")
    @RequestMapping(value = "/updateStatueById/{id}", method = RequestMethod.GET)
    public CommonResult updateStatus(@PathVariable("id")Long id,@RequestParam("status") Integer status){
        int count = mall.updateStatusById(id,status);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation(value = "根据id查找")
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public CommonResult<MallFlashPromotionSession>  select(@PathVariable("id")Long id){
        return CommonResult.success(mall.select(id));
    }

}
