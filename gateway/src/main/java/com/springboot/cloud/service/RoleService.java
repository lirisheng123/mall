package com.springboot.cloud.service;


import com.springboot.cloud.api.CommonResult;
import com.springboot.cloud.dto.MallRoleNameIntefaceName;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 20:04
 * @Version 1.0
 */
@FeignClient(contextId ="RoleService",value = "user-service")
@RequestMapping("/role")
public interface RoleService {


    @RequestMapping(value = "/selectAllRoleAndInteName", method = RequestMethod.GET)
    public CommonResult<List<MallRoleNameIntefaceName>> selectAllRoleAndInteName();

}
