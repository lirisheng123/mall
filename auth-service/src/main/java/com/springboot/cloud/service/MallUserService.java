package com.springboot.cloud.service;


import com.springboot.cloud.api.CommonResult;
import com.springboot.cloud.domain.MallUser;
import com.springboot.cloud.domain.UserRoleParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 20:02
 * @Version 1.0
 */
@FeignClient(contextId ="MallUserService",value = "user-service")
@RequestMapping("/user")
public interface MallUserService {

    @RequestMapping(value = "/selectRolesByUsername", method = RequestMethod.GET)
    public CommonResult<UserRoleParam> selectRolesByUsername(@RequestParam("username") String username);


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult registerUser(@RequestBody MallUser mallUser);

}
