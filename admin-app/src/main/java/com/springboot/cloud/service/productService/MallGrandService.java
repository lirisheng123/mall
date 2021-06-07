package com.springboot.cloud.service.productService;


import com.springboot.cloud.common.dto.MallGrandQueryParam;
import com.springboot.cloud.common.entity.MallGrand;
import com.springboot.cloud.common.util.CommonPage;
import com.springboot.cloud.common.util.CommonResult;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品品牌管理Service
 * Created by macro on 2018/4/26.
 */

@FeignClient(contextId ="Grand" ,value = "product-service")
@RequestMapping("/grand")
public interface MallGrandService {
    /**
     * 获取所有品牌
     */
    @RequestMapping(value = "/listAll",method = RequestMethod.GET)
    CommonResult<List<MallGrand>> getList();

    /**
     * 创建品牌
     */
    @PostMapping("/create")
    CommonResult create(@RequestBody MallGrand mallGrand) ;

    /**
     * 更新品牌
     */
    @PostMapping("/update/{id}")
    CommonResult update(@PathVariable("id") Long id, @RequestBody MallGrand mallGrand) ;

    /**
     * 删除品牌
     */
    @GetMapping("/delete/{id}")
    CommonResult delete(@PathVariable("id") Long id);

    /**
     * 多个参数查询品牌列表分页
     */
    @GetMapping("/list")
    CommonResult<CommonPage<MallGrand>> getList(MallGrandQueryParam mallGrandQueryParam,
                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) ;

    /**
     * 根据编号查询品牌信息
     */
    @GetMapping("/{id}")
    CommonResult<MallGrand> getItem(@PathVariable("id") Long id) ;

    /**
     * 批量删除品牌
     */
    @GetMapping("/delete/batch")
    CommonResult deleteBatch(@RequestParam("ids") List<Long> ids);


}
