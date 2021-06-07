package com.springboot.cloud.controller;

import com.springboot.cloud.service.MallUserRoleService;
import com.springboot.cloud.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/userRole")
public class MallUserRoleController {

    @Autowired
    MallUserRoleService mallUserRoleService;



    @ApiOperation("根据用户id查询角色名")
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public CommonResult<List<String>> selectByUserId(@PathVariable("id")Long userId){
        return CommonResult.success(mallUserRoleService.selectRoleNameByUserId(userId));

    }


    @ApiOperation("根据用户Id修改用户的角色分配")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public CommonResult updateRolesByUserId(@PathVariable("id") Long userId, @RequestParam List<Long> rolesId){
        int count = mallUserRoleService.updateRolesByUserId(userId,rolesId);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }

}
