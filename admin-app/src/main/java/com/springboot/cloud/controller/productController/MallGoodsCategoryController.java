package com.springboot.cloud.controller.productController;


import com.springboot.cloud.common.dto.CategoryGrand;
import com.springboot.cloud.common.entity.MallGoodsCategory;
import com.springboot.cloud.common.util.CommonPage;
import com.springboot.cloud.common.util.CommonResult;


import com.springboot.cloud.service.productService.MallGoodsCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 商品分类管理Controller
 * Created by macro on 2018/4/26.
 */
@Controller
@Api(tags = "MallGoodsCategoryController", description = "商品分类管理")
@RequestMapping("/admin/goodsCategory")
public class MallGoodsCategoryController {

    @Autowired
//    @Qualifier("mallGoodsCategoryService")
    private MallGoodsCategoryService mallGoodsCategoryService;

    @ApiOperation("添加产品分类")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@Validated @RequestBody CategoryGrand categoryGrand) {
        return  mallGoodsCategoryService.create(categoryGrand);
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
    public CommonResult update(@PathVariable Long id,
                         @Validated
                         @RequestBody MallGoodsCategory mallGoodsCategory) {
        return  mallGoodsCategoryService.update(id,mallGoodsCategory);
    }

    @ApiOperation("分页查询商品分类")
    @RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<MallGoodsCategory>> getList(@PathVariable Long parentId,
                                                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
         return  mallGoodsCategoryService.getList(parentId,pageSize,pageNum);
    }



    /**
     * 这里目前只提供二级分类的删除,暂不提供一级分类删除
     * @param id
     * @return
     */
    @ApiOperation("删除商品分类")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        return  mallGoodsCategoryService.delete(id);
    }


}
