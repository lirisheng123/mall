package com.springboot.cloud.service.userService;

import com.springboot.cloud.common.util.CommonResult;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 22:03
 * @Version 1.0
 */
@FeignClient(contextId ="MallUserRoleService",value = "user-service")
@RequestMapping("/userRole")
public interface MallUserRoleService {


    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public CommonResult<List<String>> selectByUserId(@PathVariable("id")Long userId);


    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public CommonResult updateRolesByUserId(@PathVariable("id") Long userId, @RequestParam("rolesId") List<Long> rolesId);
}
