package com.springboot.cloud.controller.userController;


import com.springboot.cloud.common.dto.user.MallRoleIntefaceParam;
import com.springboot.cloud.common.util.CommonResult;
import com.springboot.cloud.service.userService.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
//    @Qualifier("roleService")
    RoleService roleService;


    @ApiOperation("添加角色")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody MallRoleIntefaceParam mallRoleIntefaceParam){
       return  roleService.create(mallRoleIntefaceParam);
    }


    @ApiOperation("根据角色id修改角色")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult  update(@PathVariable("id") Long roleId, @RequestBody MallRoleIntefaceParam mallRoleIntefaceParam){
       return  roleService.update(roleId,mallRoleIntefaceParam);
    }

    @ApiOperation("删除角色")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult delete(@PathVariable("id") Long roleId){
        return  roleService.delete(roleId);
    }



    @ApiOperation("根据roleId来查询角色")
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public CommonResult<MallRoleIntefaceParam> selectByRoleId(@PathVariable("id")Long roleId){
        return roleService.selectByRoleId(roleId);

    }

    @ApiOperation("查询所有角色")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<List<MallRoleIntefaceParam>> selectList(){
        return roleService.selectList();

    }
}
