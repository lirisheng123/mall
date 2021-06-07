package com.springboot.cloud.controller;

import com.springboot.cloud.entity.MallGoodsProperty;
import com.springboot.cloud.entity.MallGoodsPropertyRelate;
import com.springboot.cloud.service.MallGoodsPropertyRelateService;
import com.springboot.cloud.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/3/21 17:14
 * @Version 1.0
 */
@Controller
@Api(tags = "MallGoodsPropertyRelateController", description = "商品与属性的relate")
@RequestMapping("/goodsPropertyRelate")
@ResponseBody
public class MallGoodsPropertyRelateController {

    @Autowired
    MallGoodsPropertyRelateService mallGoodsPropertyRelateService;

    @ApiOperation("查找所有")
    @RequestMapping(value = "/selectByGoodId/{id}", method = RequestMethod.GET)
    public CommonResult<List<MallGoodsPropertyRelate>>  selectByGoodId(@PathVariable("id")Long id) {
        return  CommonResult.success(mallGoodsPropertyRelateService.selectByGoodId(id));
    }
}
