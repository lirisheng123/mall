package com.springboot.cloud.controller;

import com.springboot.cloud.dto.MallGrandQueryParam;
import com.springboot.cloud.entity.MallGoodsCategory;
import com.springboot.cloud.entity.MallGrand;
import com.springboot.cloud.service.MallGrandService;
import com.springboot.cloud.util.CommonPage;
import com.springboot.cloud.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品品牌管理Controller
 * Created by macro on 2018/4/26.
 */
@Controller
@ResponseBody
@Api("商品品牌服务")
@RequestMapping("/brand")
@Slf4j
public class MallGrandController {

    @Autowired
    private MallGrandService brandService;

    @ApiOperation(value = "获取全部品牌列表")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public CommonResult<CommonPage<MallGrand>> getAllList( @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        log.info("进入获取全部品牌列表接口");
        System.out.println("进入获取全部品牌列表接口");
        List<MallGrand> mallGrands = brandService.listAllBrand(pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(mallGrands));

    }

    @ApiOperation(value = "添加品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create( @RequestBody MallGrand mallGrand) {
        //测试
        mallGrand.setCreateUser(1);
        mallGrand.setUpdateUser(1);
        CommonResult commonResult;
        int count = brandService.createBrand(mallGrand);
        if (count == 1) {
            commonResult = CommonResult.success(count);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

    @ApiOperation(value = "更新品牌")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable("id") Long id, @RequestBody MallGrand mallGrand) {
        CommonResult commonResult;
        int count = brandService.updateBrand(id,mallGrand);
        if (count == 1) {
            commonResult = CommonResult.success(count);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

    @ApiOperation(value = "删除品牌")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult delete(@PathVariable("id") Long id) {
        int count = brandService.deleteBrand(id);
        if (count == 1) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "多个参数查询品牌列表分页")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<MallGrand>> getList(MallGrandQueryParam mallGrandQueryParam,
                                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {


       System.out.println("mallgrand:"+mallGrandQueryParam);
        List<MallGrand> brandList = brandService.listBrand(mallGrandQueryParam, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(brandList));

    }

    @ApiOperation(value = "根据编号查询品牌信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<MallGrand> getItem(@PathVariable("id") Long id) {
        return CommonResult.success(brandService.getBrand(id));
    }

    @ApiOperation(value = "批量删除品牌")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    public CommonResult deleteBatch(@RequestParam("ids") List<Long> ids) {
        int count = brandService.deleteBrand(ids);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

//    @ApiOperation(value = "批量更新显示状态")
//    @RequestMapping(value = "/update/showStatus", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult updateShowStatus(@RequestParam("ids") List<Long> ids,
//                                   @RequestParam("showStatus") Integer showStatus) {
//        int count = brandService.updateShowStatus(ids, showStatus);
//        if (count > 0) {
//            return CommonResult.success(count);
//        } else {
//            return CommonResult.failed();
//        }
//    }
//
//    @ApiOperation(value = "批量更新厂家制造商状态")
//    @RequestMapping(value = "/update/factoryStatus", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult updateFactoryStatus(@RequestParam("ids") List<Long> ids,
//                                      @RequestParam("factoryStatus") Integer factoryStatus) {
//        int count = brandService.updateFactoryStatus(ids, factoryStatus);
//        if (count > 0) {
//            return CommonResult.success(count);
//        } else {
//            return CommonResult.failed();
//        }
//    }
}
