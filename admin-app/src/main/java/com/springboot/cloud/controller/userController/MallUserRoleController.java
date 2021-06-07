package com.springboot.cloud.controller.userController;


import com.springboot.cloud.common.util.CommonResult;
import com.springboot.cloud.service.userService.MallUserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 22:28
 * @Version 1.0
 */
@Controller
@ResponseBody
@Api(tags = "MallUserRoleController", description = "用户-角色管理")
@RequestMapping("/admin/userRole")
public class MallUserRoleController {

    @Autowired
//    @Qualifier("mallUserRoleService")
    MallUserRoleService mallUserRoleService;


    @ApiOperation("根据用户id查询角色名")
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public CommonResult<List<String>> selectByUserId(@PathVariable("id")Long userId){
        return mallUserRoleService.selectByUserId(userId);

    }

    @ApiOperation("根据用户Id修改用户的角色分配")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public CommonResult updateRolesByUserId(@PathVariable("id") Long userId, @RequestParam("rolesId") List<Long> rolesId){
        return  mallUserRoleService.updateRolesByUserId(userId,rolesId);

    }


}
