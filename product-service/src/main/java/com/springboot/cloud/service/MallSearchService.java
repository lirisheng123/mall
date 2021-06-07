package com.springboot.cloud.service;

import com.springboot.cloud.dto.EsProduct;
import com.springboot.cloud.util.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/4/11 15:26
 * @Version 1.0
 */
@FeignClient(contextId ="MallSearchService",value = "mall-search")
@RequestMapping("/esProduct")
public interface MallSearchService {


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult<Object> delete(@PathVariable("id") Long id) ;


    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    public CommonResult<Object> delete(@RequestParam("ids") List<Long> ids);


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult<EsProduct> create(@RequestBody EsProduct esProduct);
}
