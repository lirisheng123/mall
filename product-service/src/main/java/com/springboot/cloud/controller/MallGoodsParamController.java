package com.springboot.cloud.controller;

import com.springboot.cloud.entity.MallGoodsParam;
import com.springboot.cloud.service.MallGoodsParamService;
import com.springboot.cloud.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/19 11:23
 * @Version 1.0
 */
@Controller
@Api(tags = "MallGoodsParamController", description = "商品参数管理")
@RequestMapping("/goodsParam")
@ResponseBody
public class MallGoodsParamController {

    @Autowired
    MallGoodsParamService mallGoodsParamService;


    @ApiOperation("根据商品id查找商品参数")
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public CommonResult<List<MallGoodsParam>> selectGoodsParamByGoodId(@PathVariable("id") Long  goodId){
        return CommonResult.success(mallGoodsParamService.selectGoodsParamByGoodId(goodId));
    }


    @ApiOperation("根据id修改商品参数")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult updateByGoodsParamId(@PathVariable("id")Long goodsParamId,@RequestBody  MallGoodsParam mallGoodsParam ){
        int count = mallGoodsParamService.updateByGoodsParamId(goodsParamId,mallGoodsParam);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

}
