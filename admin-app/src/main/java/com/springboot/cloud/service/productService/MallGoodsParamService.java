package com.springboot.cloud.service.productService;


import com.springboot.cloud.common.entity.MallGoodsParam;
import com.springboot.cloud.common.util.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/19 11:22
 * @Version 1.0
 */

@FeignClient(contextId ="GoodsParam" ,value = "product-service")
@RequestMapping("/goodsParam")
public interface MallGoodsParamService {

    /**
     * 根据商品id查找商品参数
     */
    @GetMapping("/select/{id}")
    CommonResult<List<MallGoodsParam>> selectGoodsParamByGoodId(@PathVariable("id") Long  goodId);

    /**
     * 根据id修改商品参数
     */
    @PostMapping("/update/{id}")
    CommonResult updateByGoodsParamId(@PathVariable("id")Long goodsParamId, @RequestBody MallGoodsParam mallGoodsParam );


}
