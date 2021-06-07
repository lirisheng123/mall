package com.springboot.cloud.service.productService;


import com.springboot.cloud.common.entity.MallCategoryGrand;
import com.springboot.cloud.common.util.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/18 10:36
 * @Version 1.0
 */
@FeignClient(contextId ="CategoryGrand",value = "product-service")
@RequestMapping("/categoryGrand")
public interface MallCategoryGrandService {

    /**
     * 删除某个分类的所有的品牌关联关系
     * @param categoryId
     * @return
     */
    @GetMapping("/delete/{id}")
    CommonResult deleteCategoryGrand(@PathVariable("id") Long categoryId);

    /**
     *查询某个分类的所有的品牌
     */
    @GetMapping("/list/{id}")
    CommonResult<String> listByCategoryId(@PathVariable("id") Long categoryId);

    /**
     * 更改某个分类的所有的品牌
     */
    @PostMapping("/update/{id}")
    CommonResult updateByCategoryId(@PathVariable("id") Long categoryId,@RequestBody List<MallCategoryGrand> mallCategoryGrand);


}
