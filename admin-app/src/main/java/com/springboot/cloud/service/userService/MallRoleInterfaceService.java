package com.springboot.cloud.service.userService;

import com.springboot.cloud.common.util.CommonResult;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 22:35
 * @Version 1.0
 */
@FeignClient(contextId ="MallRoleInterfaceService",value = "user-service")
@RequestMapping("/roleInterface")
public interface MallRoleInterfaceService {




    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public CommonResult<List<String>> selectByRoleId(@PathVariable("id")Long roleId);

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public CommonResult updateIntefaceByRoleId(@PathVariable("id") Long roleId, @RequestParam("inteId") List<Long> inteId);

}
