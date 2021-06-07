package com.springboot.cloud.controller;

import com.springboot.cloud.dto.UserParam;
import com.springboot.cloud.dto.UserRoleParam;
import com.springboot.cloud.entity.MallUserAddress;
import com.springboot.cloud.service.UserAddressService;
import com.springboot.cloud.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 20:25
 * @Version 1.0
 */
@Controller
@ResponseBody
@Api(tags = "UserAddressController", description = "用户地址管理")
@RequestMapping("/userAddress")
@Slf4j
public class UserAddressController {
    @Autowired
    UserAddressService userAddressService;



    @ApiOperation("添加用户地址")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody MallUserAddress mallUserAddress){
        log.debug("mallUserAddress:"+mallUserAddress);
        int count = userAddressService.create(mallUserAddress);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @ApiOperation("根据id修改用户地址")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable("id")Long userAddressId, @RequestBody  MallUserAddress mallUserAddress){
        int count = userAddressService.update(userAddressId, mallUserAddress);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();

    }


    @ApiOperation("根据id查询用户地址")
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<MallUserAddress> select(@PathVariable("id") Long userAddressId){
        MallUserAddress addresse = userAddressService.select(userAddressId);
        return CommonResult.success(addresse);
    }


    @ApiOperation("根据id删除用户地址")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult delete(@PathVariable("id") Long userAddressId){
        int count = userAddressService.delete(userAddressId);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @ApiOperation("根据用户id查询用户地址")
    @RequestMapping(value = "/list/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public  CommonResult<List<MallUserAddress>> selectListByUserId(@PathVariable("userId")Long userId){
        return CommonResult.success(userAddressService.selectListByUserId(userId));
    }

    /**
     * 根据id修改用户地址的默认状态
     */
    @ApiOperation("根据id修改用户地址的默认状态")
    @RequestMapping(value = "/updateDefaultFlag/{id}", method = RequestMethod.GET)
    @ResponseBody
    public  CommonResult updateDefaultFlag(@PathVariable("id")Long addressId,@RequestParam  Long userId){
        int count = userAddressService.updateDefaultFlag(userId,addressId);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @ApiOperation("根据userId查询默认地址")
    @RequestMapping(value = "/selectDefault/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<MallUserAddress> selectDefaultByUserId(@PathVariable("userId")Long userId){
        MallUserAddress addresse = userAddressService.selectDefaultByUserId(userId);
        return CommonResult.success(addresse);
    }


}
