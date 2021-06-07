package com.springboot.cloud.controller.userController;


import com.springboot.cloud.common.entity.user.MallInterface;
import com.springboot.cloud.common.util.CommonResult;
import com.springboot.cloud.service.userService.MallInterfaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 20:04
 * @Version 1.0
 */
@Controller
@ResponseBody
@Api(tags = "InterfaceController", description = "接口管理")
@RequestMapping("/admin/interface")
public class InterfaceController {
    
    @Autowired
//    @Qualifier("mallInterfaceService")
    MallInterfaceService mallInterfaceService;

    @ApiOperation("添加接口")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody MallInterface mallInterface){
        return mallInterfaceService.create(mallInterface);
    }


    @ApiOperation("根据id修改接口")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult  update(@PathVariable("id") Long inteId, @RequestBody MallInterface mallInterface){
        return  mallInterfaceService.update(inteId,mallInterface);
    }

    @ApiOperation("删除接口")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult delete(@PathVariable("id") Long inteId){
       return  mallInterfaceService.delete(inteId);
    }



    @ApiOperation("根据id来查询接口")
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public CommonResult<MallInterface> selectByInterId(@PathVariable("id")Long inteId){
        return  mallInterfaceService.selectByInterId(inteId);

    }

    @ApiOperation("查询所有接口")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<List<MallInterface>> selectList(){
       return  mallInterfaceService.selectList();

    }
    
}
