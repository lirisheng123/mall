package com.springboot.cloud.controller;

import com.springboot.cloud.entity.MallGoodsGrand;
import com.springboot.cloud.service.MallGoodsGrandService;
import com.springboot.cloud.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: lirisheng
 * @Date: 2021/3/20 17:49
 * @Version 1.0
 */
@Controller
@ResponseBody
@Api(tags = "MallGoodsGrandController", description = "商品的参数管理")
@RequestMapping("/goodsGrand")
public class MallGoodsGrandController {
    @Autowired
    MallGoodsGrandService mallGoodsGrandService;


    @ApiOperation(value = "添加商品与品牌的关联")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult add(MallGoodsGrand mallGoodsGrand) {

        int count = mallGoodsGrandService.add(mallGoodsGrand);
        if (count == 1) {
            return CommonResult.success(count);
        } else {
            return  CommonResult.failed();
        }


    }

    @ApiOperation(value = "根据商品的id来查找品牌")
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public CommonResult<MallGoodsGrand> selectGrandByGoodId(@PathVariable("id") Long goodId) {
        return CommonResult.success(mallGoodsGrandService.selectGrandByGoodId(goodId));
    }
}
