package com.springboot.cloud.controller;

import com.springboot.cloud.dto.MallRoleIntefaceParam;
import  com.springboot.cloud.dto.MallRoleNameIntefaceName;

import com.springboot.cloud.service.RoleService;
import com.springboot.cloud.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 20:03
 * @Version 1.0
 */
@Controller
@ResponseBody
@Api(tags = "RoleController", description = "角色管理")
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;


    @ApiOperation("添加角色")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody MallRoleIntefaceParam mallRoleIntefaceParam){
        int count = roleService.create(mallRoleIntefaceParam);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("根据角色id修改角色")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult  update(@PathVariable("id") Long roleId, @RequestBody MallRoleIntefaceParam mallRoleIntefaceParam){
        int count = roleService.update(roleId,mallRoleIntefaceParam);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除角色")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult delete(@PathVariable("id") Long roleId){
        int count = roleService.delete(roleId);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }



    @ApiOperation("根据roleId来查询角色")
    public CommonResult<MallRoleIntefaceParam> selectByRoleId(@PathVariable("id")Long roleId){
        return CommonResult.success(roleService.selectByRoleId(roleId));

    }

    @ApiOperation("查询所有角色")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult< List<MallRoleIntefaceParam>> selectList(){
        return CommonResult.success(roleService.list());

    }

//    @ApiOperation("查询所有角色")
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public CommonResult< List<MallRoleIntefaceParam>> selectList(){
//        return CommonResult.success(roleService.list());
//
//    }


    @ApiOperation("通过接口名来查询角色组组")
    @RequestMapping(value = "/selectRoleNameByIntefaces", method = RequestMethod.GET)
    public CommonResult<List<String>> selectRoleNameByIntefaces(@RequestParam("inteName") String inteName){
        return CommonResult.success(roleService.selectRoleNameByIntefaces(inteName));
    }

    @ApiOperation("查询所有")
    @RequestMapping(value = "/selectAllRoleAndInteName", method = RequestMethod.GET)
    public CommonResult<List<MallRoleNameIntefaceName>> selectAllRoleAndInteName(){
        return CommonResult.success(roleService.selectAllRoleAndInteName());
    }
}
