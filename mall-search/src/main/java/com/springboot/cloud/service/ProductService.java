package com.springboot.cloud.service;

import com.springboot.cloud.domain.EsProduct;
import com.springboot.cloud.util.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/4/11 15:35
 * @Version 1.0
 */

@FeignClient(contextId ="ProductService" ,value = "product-service")
@RequestMapping("/product")
public interface ProductService {


    @RequestMapping(value = "/esProductList", method = RequestMethod.GET)
    public CommonResult<List<EsProduct>> getAllEsProduct();
}
