package com.springboot.cloud.controller;


import com.springboot.cloud.dto.CategoryGrand;
import com.springboot.cloud.dto.CategoryParentAndChird;
import com.springboot.cloud.entity.MallGoodsCategory;
import com.springboot.cloud.service.MallGoodsCategoryService;
import com.springboot.cloud.util.CommonPage;
import com.springboot.cloud.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品分类管理Controller
 * Created by macro on 2018/4/26.
 */
@Controller
@Api(tags = "MallGoodsCategoryController", description = "商品分类管理")
@RequestMapping("/goodsCategory")
public class MallGoodsCategoryController {
    @Autowired
    private MallGoodsCategoryService mallGoodsCategoryService;

    @ApiOperation("添加产品分类")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@Validated @RequestBody CategoryGrand categoryGrand) {
        categoryGrand.setCreateUser(1);
        categoryGrand.setUpdateUser(1);
        categoryGrand.setIsDeleted((byte)0);
        if(categoryGrand.getParentId()==0){
            categoryGrand.setCategoryLevel((byte)1);
        }
        else{
            categoryGrand.setCategoryLevel((byte) 2);
        }
        int count = mallGoodsCategoryService.create(categoryGrand);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    /**
     * 注意 这里规定,分类的级别是不可以进行修改
     *              一级分类的parentId只能为0
     *
     * @param id
     * @param mallGoodsCategory
     * @return
     */
    @ApiOperation("修改商品分类")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable("id") Long id,@RequestBody MallGoodsCategory mallGoodsCategory) {
        int count = mallGoodsCategoryService.update(id, mallGoodsCategory);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("分页查询商品分类")
    @RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<MallGoodsCategory>> getList(@PathVariable Long parentId,
                                                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<MallGoodsCategory> productCategoryList = mallGoodsCategoryService.getList(parentId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(productCategoryList));
    }

//    @ApiOperation("根据id获取商品分类")
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<PmsProductCategory> getItem(@PathVariable Long id) {
//        PmsProductCategory productCategory = productCategoryService.getItem(id);
//        return CommonResult.success(productCategory);
//    }

    /**
     * 这里目前只提供二级分类的删除,暂不提供一级分类删除
     * @param id
     * @return
     */
    @ApiOperation("删除商品分类")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        //查询该id是否为1级,判断是否有2级的引用,有,则不删
        //如果为2级,判断商品的依赖是否存在
        if(mallGoodsCategoryService.judgeDelete(id)==0){
            return CommonResult.failed("有依赖,不能进行删除");
        }

        int count = mallGoodsCategoryService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed("删除失败");
        }
    }

    @ApiOperation("根据id获取商品分类")
    @RequestMapping(value = "/getItem/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<MallGoodsCategory> getItem(@PathVariable Long id) {
        return  CommonResult.success(mallGoodsCategoryService.getItem(id));
    }

    @ApiOperation("查询所有一级分类及子分类")
    @RequestMapping(value = "/list/withChildren", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<CategoryParentAndChird>> listWithChildren() {
        List<CategoryParentAndChird> list = mallGoodsCategoryService.listWithChildren();
        return CommonResult.success(list);
    }



//    @ApiOperation("修改导航栏显示状态")
//    @RequestMapping(value = "/update/navStatus", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult updateNavStatus(@RequestParam("ids") List<Long> ids, @RequestParam("navStatus") Integer navStatus) {
//        int count = productCategoryService.updateNavStatus(ids, navStatus);
//        if (count > 0) {
//            return CommonResult.success(count);
//        } else {
//            return CommonResult.failed();
//        }
//    }
//
//    @ApiOperation("修改显示状态")
//    @RequestMapping(value = "/update/showStatus", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult updateShowStatus(@RequestParam("ids") List<Long> ids, @RequestParam("showStatus") Integer showStatus) {
//        int count = productCategoryService.updateShowStatus(ids, showStatus);
//        if (count > 0) {
//            return CommonResult.success(count);
//        } else {
//            return CommonResult.failed();
//        }
//    }

//    @ApiOperation("查询所有一级分类及子分类")
//    @RequestMapping(value = "/list/withChildren", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<List<PmsProductCategoryWithChildrenItem>> listWithChildren() {
//        List<PmsProductCategoryWithChildrenItem> list = productCategoryService.listWithChildren();
//        return CommonResult.success(list);
//    }
}
