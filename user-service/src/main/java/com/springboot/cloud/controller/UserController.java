package com.springboot.cloud.controller;


import cn.hutool.core.collection.CollUtil;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.nimbusds.jose.JWSObject;
import com.springboot.cloud.dto.UpdateLockedParam;
import com.springboot.cloud.dto.UserParam;
import com.springboot.cloud.dto.UserRoleParam;
import com.springboot.cloud.entity.MallUser;
import com.springboot.cloud.service.MallUserService;
import com.springboot.cloud.util.CommonPage;
import com.springboot.cloud.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 20:00
 * @Version 1.0
 */
@Controller
@ResponseBody
@Api(tags = "UserController", description = "用户管理")
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    MallUserService mallUserService;

    @ApiOperation("添加用户")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody UserRoleParam userRoleParam){
        int count = mallUserService.create(userRoleParam);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改用户")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult  updateByUserId(@PathVariable("id") Long userId, @RequestBody  UserRoleParam userRoleParam){
        int count = mallUserService.updateByUserId(userId,userRoleParam);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }

    @ApiOperation("批量修改锁定或开锁用户账号")
    @RequestMapping(value = "/updateLock", method = RequestMethod.GET)
    public CommonResult  updateLockListByUserId(UpdateLockedParam updateLockedParam){

        int count = mallUserService.updateLock(updateLockedParam.getUserId(),updateLockedParam.getStatusLocked());
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }

    /**
     * 暂不提供账号的删除操作
     * @param userId
     * @return
     */
    @ApiOperation("删除用户")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult delete(@PathVariable("id") Long userId){
        int count = mallUserService.delete(userId);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("根据用户id查询用户")
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public CommonResult<UserRoleParam> selectByUserId(@PathVariable("id")Long userId){
        return CommonResult.success(mallUserService.selectByUserId(userId));

    }

    @ApiOperation("多个参数来分页查询用户")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<UserRoleParam>> selectList(UserParam userParam,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        List<UserRoleParam> userList = mallUserService.selectList(userParam,pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(userList));
    }
    @ApiOperation("通过loginName来查询用户和角色组")
    @RequestMapping(value = "/selectRolesByUsername", method = RequestMethod.GET)
    public CommonResult<UserRoleParam> selectRolesByUsername(@RequestParam("username") String username){
        return   CommonResult.success(mallUserService.selectRolesByUsername(username));
    }
    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAdminInfo( Principal principal) {
        log.debug("principal:"+principal);
        if(principal==null){
            return CommonResult.unauthorized(null);
        }
        String username = principal.getName();
        log.debug("username:"+username);
        UserRoleParam userRoleParam = mallUserService.selectRolesByUsername(username);
        Map<String, Object> data = new HashMap<>();
        data.put("username", userRoleParam.getLoginName());
//        data.put("menus", roleService.getMenuList(umsAdmin.getId()));
//        data.put("icon", );
//        List<UmsRole> roleList = adminService.getRoleList(umsAdmin.getId());
//        if(CollUtil.isNotEmpty(roleList)){
//            List<String> roles = roleList.stream().map(UmsRole::getName).collect(Collectors.toList());
//            data.put("roles",roles);
//        }
        data.put("roles",userRoleParam.getRoleNames());
        log.debug("data:"+data);
        return CommonResult.success(data);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/infoTest", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAdminInfoTest(HttpServletRequest request) {

//        打印所有的headers
//        Enumeration<String> e = request.getHeaderNames();
//        while(e.hasMoreElements()){
//            String headerName = e.nextElement();//透明称
//            Enumeration<String> headerValues = request.getHeaders(headerName);
//            while(headerValues.hasMoreElements()){
//                log.debug(headerName+":"+headerValues.nextElement());
//            }
//        }

        String token= request.getHeader("authorizationtest");

        if(token==null){
            return CommonResult.failed("没有 token");
        }

        //从token中解析用户信息并设置到Header中去
        log.debug("token:"+token);
        try {
            //从token中解析用户信息并设置到Header中去
            String realToken = token.replace("Bearer ", "");
            JWSObject jwsObject = JWSObject.parse(realToken);
            String userStr = jwsObject.getPayload().toString();
            Gson gson = new Gson();
            Map<String, Object> rtMap = gson.fromJson(userStr,new TypeToken<Map<String,Object>>() {}.getType());
            log.info("rtMap:"+rtMap);
            Map<String,Object> map = new HashMap<>();
            map.put("username",rtMap.get("user_name"));
            map.put("roles",rtMap.get("authorities"));
            Integer  userId = ((Double)rtMap.get("id")) .intValue();
            map.put("userId",userId);
            return CommonResult.success(map);
        } catch (Exception parse) {
            return CommonResult.failed("token解析失败或者json转化失败");
//            e.printStackTrace();
        }




//        log.debug("username:"+username);
//        UserRoleParam userRoleParam = mallUserService.selectRolesByUsername(username);
//        Map<String, Object> data = new HashMap<>();
//        data.put("username", userRoleParam.getLoginName());
//
//        data.put("roles",userRoleParam.getRoleNames());
//        log.debug("data:"+data);

    }


    @ApiOperation(value = "更新用户的角色")
    @RequestMapping(value = "/allocRole/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult updateRoleListByUserId(@PathVariable("id") Long id, List<Long> roleIds) {
        int count = mallUserService.updateRoleListByUserId(id,roleIds);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }

    @ApiOperation(value = "注册用户")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult registerUser(@RequestBody  MallUser mallUser){
        int count = mallUserService.registerUser(mallUser);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }


    }



}
