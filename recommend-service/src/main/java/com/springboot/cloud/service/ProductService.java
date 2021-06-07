package com.springboot.cloud.service;


import com.springboot.cloud.entity.MallGoodsInfo;
import com.springboot.cloud.util.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: lirisheng
 * @Date: 2021/3/27 20:30
 * @Version 1.0
 */
@FeignClient(contextId ="GoodsProperty" ,value = "product-service")
public interface ProductService {

    /**
     * 根据商品id获取商品编辑信息
     */
    @RequestMapping(value = "/product/updateInfo/{id}", method = RequestMethod.GET)
    CommonResult<MallGoodsInfo> getUpdateInfo(@PathVariable("id") Long id);


}
