package com.springboot.cloud.controller;

import com.springboot.cloud.api.CommonResult;
import com.springboot.cloud.domain.MallUser;
import com.springboot.cloud.service.MallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: lirisheng
 * @Date: 2021/5/9 15:32
 * @Version 1.0
 */
@RestController
public class RegisterController {

    @Autowired
    MallUserService mallUserService;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult registerUser(@RequestBody MallUser mallUser){
        return mallUserService.registerUser(mallUser);

    }

}
