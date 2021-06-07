package com.springboot.cloud.service.userService;

import com.springboot.cloud.common.entity.user.MallInterface;
import com.springboot.cloud.common.util.CommonResult;


import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 20:04
 * @Version 1.0
 */
@FeignClient(contextId ="MallInterfaceService",value = "user-service")
@RequestMapping("/interface")
public interface MallInterfaceService {



    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody MallInterface mallInterface);



    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable("id") Long inteId, @RequestBody MallInterface mallInterface);


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult delete(@PathVariable("id") Long inteId);




    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public CommonResult<MallInterface> selectByInterId(@PathVariable("id")Long inteId);


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<List<MallInterface>> selectList();
}
