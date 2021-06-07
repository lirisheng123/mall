package com.springboot.cloud.controller.userController;


import com.springboot.cloud.common.util.CommonResult;
import com.springboot.cloud.service.userService.MallRoleInterfaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 22:55
 * @Version 1.0
 */
@Controller
@ResponseBody
@Api(tags = "MallRoleInterfaceController", description = "角色-接口管理")
@RequestMapping("/admin/roleInterface")
public class MallRoleInterfaceController {

    @Autowired
//    @Qualifier("mallRoleInterfaceService")
    MallRoleInterfaceService mallRoleInterfaceService;

    @ApiOperation("根据角色id查询接口名")
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public CommonResult<List<String>> selectByRoleId(@PathVariable("id")Long roleId){
        return  mallRoleInterfaceService.selectByRoleId(roleId);

    }


    @ApiOperation("根据角色Id修改角色的接口分配")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public CommonResult updateIntefaceByRoleId(@PathVariable("id") Long roleId, @RequestParam List<Long> inteId){
      return  mallRoleInterfaceService.updateIntefaceByRoleId(roleId,inteId);

    }
}
