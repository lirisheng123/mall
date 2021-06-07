package com.springboot.cloud.controller.userController;


import com.google.gson.Gson;
import com.springboot.cloud.common.dto.user.UserParam;
import com.springboot.cloud.common.dto.user.UserRoleParam;
import com.springboot.cloud.common.util.CommonPage;
import com.springboot.cloud.common.util.CommonResult;
import com.springboot.cloud.service.userService.MallUserService;
import com.sun.net.httpserver.HttpPrincipal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 20:00
 * @Version 1.0
 */
@Controller
@ResponseBody
@Api(tags = "UserController", description = "用户管理")
@RequestMapping("/admin/user")
@Slf4j
public class UserController {

    @Autowired
//    @Qualifier("mallUserService")
    MallUserService mallUserService;



    @ApiOperation("添加用户")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody UserRoleParam userRoleParam){
        return  mallUserService.create(userRoleParam);
    }

    @ApiOperation("修改用户")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult  updateByUserId(@PathVariable("id") Long userId, @RequestBody  UserRoleParam userRoleParam){
       return  mallUserService.updateByUserId(userId,userRoleParam);

    }




    @ApiOperation("批量修改锁定或开锁用户账号")
    @RequestMapping(value = "/updateLock", method = RequestMethod.GET)
    public CommonResult  updateLockListByUserId(@RequestParam List<Long> userId, @RequestParam   Long statusLocked){
       return  mallUserService.updateLockListByUserId(userId,statusLocked);

    }

    /**
     * 暂不提供账号的删除操作
     * @param userId
     * @return
     */
    @ApiOperation("删除用户")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult delete(@PathVariable("id") Long userId){
        return  mallUserService.delete(userId);
    }


    @ApiOperation("根据用户id查询用户")
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public CommonResult<UserRoleParam> selectByUserId(@PathVariable("id")Long userId){
        return mallUserService.selectByUserId(userId);

    }

    @ApiOperation("多个参数来分页查询用户")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<UserRoleParam>> selectList(UserParam userParam,
                                                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
       return  mallUserService.selectList(userParam,pageSize,pageNum);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAdminInfo(HttpServletRequest request) {
        String json = request.getHeader("user");
        log.debug("json in info:"+json);
        Gson gson = new Gson();
        Map map = gson.fromJson(json, Map.class);
        String username =(String) map.get("user_name");
        List<String> roles =(List<String>)map.get("authorities");
        Map<String, Object> data = new HashMap<>();
        data.put("username", username);
        data.put("roles",roles);
        log.debug("data:"+data);
        return CommonResult.success(data);
//        UserRoleParam userRoleParam = mallUserService.selectRolesByUsername(username);
//        return  mallUserService.getAdminInfo(principal);
    }
}
