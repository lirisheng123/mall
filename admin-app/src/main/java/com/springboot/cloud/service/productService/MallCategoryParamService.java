package com.springboot.cloud.service.productService;


import com.springboot.cloud.common.entity.MallCategoryParam;
import com.springboot.cloud.common.util.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/18 16:58
 * @Version 1.0
 */

@FeignClient(contextId ="CategoryParam" ,value = "product-service")
@RequestMapping("/categoryParam")
public interface MallCategoryParamService {

    /**
     * 添加分类与参数的关联关系
     * @param mallCategoryParam
     * @return
     */
    @PostMapping("/create")
    CommonResult create(@RequestBody MallCategoryParam mallCategoryParam);

    /**
     * 根据id选择分类与参数的关联关系
     * @param mallCategoryParamId
     * @return
     */
    @GetMapping("/item/{id}")
    CommonResult<MallCategoryParam> selectByCategoryParamId(@PathVariable("id") Long goodParamId);

    /**
     * 根据id删除分类与参数的关联关系
     * @param mallCategoryParamId
     * @return
     */
    @GetMapping("/deleteItem/{id}")
    CommonResult deleteByCategoryParamId(@PathVariable("id") Long goodParamId);



    /**
     * 根据id更改分类与参数的关联关系
     * @param mallCategoryParam
     * @return
     */
    @PostMapping("/update/{id}")
    CommonResult  updateByCategoryParamId(@PathVariable("id") Long categoryParamId,@RequestBody  MallCategoryParam mallCategoryParam);

    /**
     * 根据分类的id选择分类与参数的关联关系
     * @param categoryId
     * @return
     */
    @GetMapping("/list/{id}")
    CommonResult<List<MallCategoryParam>> selectByCategoryId(@PathVariable("id") Long categoryId );

    /**
     * 根据分类的id选择分类与参数的关联关系
     * @param categoryId
     * @return
     */
    @GetMapping("/delete/{id}")
    CommonResult deleteByCategoryId(@PathVariable("id")  Long categoryId );


}
