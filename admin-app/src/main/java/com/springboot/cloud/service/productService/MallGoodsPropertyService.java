package com.springboot.cloud.service.productService;


import com.springboot.cloud.common.entity.MallGoodsProperty;
import com.springboot.cloud.common.util.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品属性管理Service
 * Created by macro on 2018/4/26.
 */


@FeignClient(contextId ="GoodsProperty" ,value = "product-service")
@RequestMapping("/goodsProperty")
public interface MallGoodsPropertyService {
    /**
     * 添加分类与参数的关联关系添加商品属性
     */
    @PostMapping("/create")
    CommonResult create(@RequestBody MallGoodsProperty mallGoodsProperty);


    /**
     * 根据id修改商品属性
     */
    @PostMapping("/update/{id}")
    CommonResult update(@PathVariable("id") Long id, @RequestBody  MallGoodsProperty mallGoodsProperty);


    /**
     * 获取id获取单个商品属性信息
     */
    @GetMapping("/item/{id}")
    CommonResult<MallGoodsProperty>  getItem(@PathVariable("id") Long id);

    /**
     * 根据id删除商品属性
     */
    @GetMapping("/deleteItem/{id}")
    CommonResult delete( @PathVariable("id") Long id);


    /**
     * 根据商品id查找商品属性
     */
    @GetMapping("/list/{id}")
    CommonResult<List<MallGoodsProperty>> selectByGoodsId(@PathVariable("id")Long goodsId);





}
