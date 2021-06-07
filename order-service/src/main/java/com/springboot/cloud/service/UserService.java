package com.springboot.cloud.service;


import com.springboot.cloud.common.entity.user.MallUserAddress;
import com.springboot.cloud.util.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 20:25
 * @Version 1.0
 */
@FeignClient(contextId ="UserService",value = "user-service")
@RequestMapping("/userAddress")
public interface UserService {


    /**
     * 根据id查询用户地址
     */
    @GetMapping("/select/{id}")
    public CommonResult<MallUserAddress> select(@PathVariable("id") Long userAddressId);


}
