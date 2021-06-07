package com.springboot.cloud.service.userService;

import com.springboot.cloud.common.dto.user.UserParam;
import com.springboot.cloud.common.dto.user.UserRoleParam;
import com.springboot.cloud.common.util.CommonPage;
import com.springboot.cloud.common.util.CommonResult;


import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 20:02
 * @Version 1.0
 */
@FeignClient(contextId ="MallUserService",value = "user-service")
@RequestMapping("/user")
public interface MallUserService {


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody UserRoleParam userRoleParam);


    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult updateByUserId(@PathVariable("id") Long userId, @RequestBody UserRoleParam userRoleParam);


    @RequestMapping(value = "/updateLock", method = RequestMethod.GET)
    public CommonResult updateLockListByUserId(@RequestParam("userId") List<Long> userId, @RequestParam("statusLocked") Long statusLocked);

    /**
     * 暂不提供账号的删除操作
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult delete(@PathVariable("id") Long userId);


    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public CommonResult<UserRoleParam> selectByUserId(@PathVariable("id") Long userId);


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<UserRoleParam>> selectList(UserParam userParam,
                                                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum);


    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public CommonResult getAdminInfo(Principal principal);
}
