package com.springboot.cloud.controller;

import com.springboot.cloud.entity.MallGrand;
import com.springboot.cloud.entity.MallRecommend;
import com.springboot.cloud.service.MallRecommendService;
import com.springboot.cloud.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: lirisheng
 * @Date: 2021/5/5 16:06
 * @Version 1.0
 */
@Controller
@ResponseBody
@Api("推荐信息收集管理")
@RequestMapping("/recommend")
@Slf4j
public class MallRecommendController {
    @Autowired
    MallRecommendService mallRecommendService;

    @ApiOperation(value = "添加推荐的记录")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody MallRecommend mallRecommend) {
        //测试
        CommonResult commonResult;
        int count = mallRecommendService.create(mallRecommend);
        if (count == 1) {
            commonResult = CommonResult.success(count);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }



    /**
     * 根据用户id和商品id获取记录
     */
    @ApiOperation(value = "根据用户id和商品id获取记录")
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public CommonResult<MallRecommend>   selectByUserIdAndGoodId(@RequestParam("userId") Long userId,@RequestParam("goodId") Long goodId){
        return CommonResult.success(mallRecommendService.selectByUserIdAndGoodId(userId,goodId));
    }


    @ApiOperation(value = "更改,即喜好的分数")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult  update(@PathVariable("id")Long id,@RequestBody MallRecommend mallRecommend){
        CommonResult commonResult;
        int count = mallRecommendService.update(id,mallRecommend);
        if (count == 1) {
            commonResult = CommonResult.success(count);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }
}
