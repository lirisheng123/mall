package com.springboot.cloud.controller;

import com.springboot.cloud.entity.MallCompanyAddress;
import com.springboot.cloud.entity.MallCoupon;
import com.springboot.cloud.service.MallCompanyAddressService;
import com.springboot.cloud.util.CommonPage;
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
 * @Date: 2021/4/3 16:29
 * @Version 1.0
 */
@Controller
@ResponseBody
@Api(tags = "MallCompanyAddressController", description = "公司地址管理")
@RequestMapping("/companyAddress")
@Slf4j
public class MallCompanyAddressController {

    @Autowired
    MallCompanyAddressService  companyAddressService;

    @ApiOperation("填写公司地址")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult insert(@RequestBody MallCompanyAddress mallCompanyAddress){
        int count = companyAddressService.insert(mallCompanyAddress);
        if(count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("编辑公司地址")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable("id")Long id,@RequestBody MallCompanyAddress mallCompanyAddress){
        int count = companyAddressService.update(id,mallCompanyAddress);
        if(count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("分页查看公司地址")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public   CommonResult<CommonPage<MallCompanyAddress>> selectList(
                                                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        List<MallCompanyAddress> couponList = companyAddressService.selectList(pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(couponList));
    }


    @ApiOperation("根据id查看公司地址")
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public CommonResult<MallCompanyAddress>  select(@PathVariable("id")  Long id){
        return CommonResult.success(companyAddressService.select(id));
    }


    @ApiOperation("删除公司地址")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult delete(@PathVariable("id") Long id){

        int count = companyAddressService.delete(id);
        if(count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @ApiOperation("更改为默认状态")
    @RequestMapping(value = "/updateDefault/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult UpdateDefault(@PathVariable("id")Long id,
                                      @RequestParam("type") Integer type){
        int count = companyAddressService.UpdateDefault(id,type);
        if(count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @ApiOperation("查找默认状态")
    @RequestMapping(value = "/selectByType/{type}", method = RequestMethod.GET)
    public CommonResult<MallCompanyAddress> selectByType(@PathVariable("type") Integer type){
        return CommonResult.success(companyAddressService.selectByType(type));
    }


    @ApiOperation("查看公司所有的地址")
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public CommonResult<List<MallCompanyAddress> >selectAll(){
        return CommonResult.success(companyAddressService.selectAll());
    }
}
