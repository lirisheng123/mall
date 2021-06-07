package com.springboot.cloud.controller.productController;


import com.springboot.cloud.common.dto.MallGrandQueryParam;
import com.springboot.cloud.common.entity.MallGrand;
import com.springboot.cloud.common.util.CommonPage;
import com.springboot.cloud.common.util.CommonResult;
import com.springboot.cloud.service.productService.MallGrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@RequestMapping("/admin/grand")
public class MallGrandController {

    @Autowired
//    @Qualifier("mallGrandService")
    private MallGrandService brandService;

    @ApiOperation(value = "获取全部品牌列表")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<MallGrand>> getList()
    {
        System.out.println("admin:enter grandlist");
        return  brandService.getList();
    }

    @ApiOperation(value = "添加品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody MallGrand mallGrand) {
        return  brandService.create(mallGrand);
    }

    @ApiOperation(value = "更新品牌")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable("id") Long id, @RequestBody MallGrand mallGrand) {
         return  brandService.update(id,mallGrand);
    }

    @ApiOperation(value = "删除品牌")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult delete(@PathVariable("id") Long id) {
       return  brandService.delete(id);
    }

    @ApiOperation(value = "多个参数查询品牌列表分页")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<MallGrand>> getList(MallGrandQueryParam mallGrandQueryParam,
                                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {


          return  brandService.getList(mallGrandQueryParam,pageNum,pageSize);

    }

    @ApiOperation(value = "根据编号查询品牌信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<MallGrand> getItem(@PathVariable("id") Long id) {
        return  brandService.getItem(id);
    }

    @ApiOperation(value = "批量删除品牌")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteBatch(@RequestParam("ids") List<Long> ids) {
         return  brandService.deleteBatch(ids);
    }

}
