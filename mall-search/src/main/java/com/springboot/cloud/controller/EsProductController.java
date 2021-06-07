package com.springboot.cloud.controller;


import com.springboot.cloud.domain.EsProduct;
import com.springboot.cloud.domain.EsProductRelatedInfo;
import com.springboot.cloud.dto.EsProductQuery;
import com.springboot.cloud.service.EsProductService;
import com.springboot.cloud.util.CommonPage;
import com.springboot.cloud.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 搜索商品管理Controller
 * Created by macro on 2018/6/19.
 */
@Controller
@Api(tags = "EsProductController", description = "搜索商品管理")
@RequestMapping("/esProduct")
@ResponseBody
@Slf4j
public class EsProductController {

    @Autowired
    private EsProductService esProductService;

    @ApiOperation(value = "导入所有数据库中商品到ES")
    @RequestMapping(value = "/importAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> importAllList() {
        int count = esProductService.importAll();
        return CommonResult.success(count);
    }

    @ApiOperation(value = "根据id删除商品")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> delete(@PathVariable("id") Long id) {
        esProductService.delete(id);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "根据id批量删除商品")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> delete(@RequestParam("ids") List<Long> ids) {
        esProductService.delete(ids);
        return CommonResult.success(null);
    }


    @ApiOperation(value = "根据id创建商品")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<EsProduct> create(@RequestBody EsProduct esProduct1) {
        EsProduct esProduct = esProductService.create(esProduct1);
        if (esProduct != null) {
            return CommonResult.success(esProduct);
        } else {
            return CommonResult.failed();
        }
    }

//    @ApiOperation(value = "简单搜索")
//    @RequestMapping(value = "/search/simple", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<CommonPage<EsProduct>> search(@RequestParam(required = false) String keyword,
//                                                      @RequestParam(required = false, defaultValue = "0") Integer pageNum,
//                                                      @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
//        Page<EsProduct> esProductPage = esProductService.search(keyword, pageNum, pageSize);
//        return CommonResult.success(CommonPage.restPage(esProductPage));
//    }

    @ApiOperation(value = "综合搜索、筛选、排序")
//    @ApiImplicitParam(name = "sort", value = "排序字段:0->按相关度；1->按新品；2->按销量；3->价格从低到高；4->价格从高到低",
//            defaultValue = "0", allowableValues = "0,1,2,3,4", paramType = "query", dataType = "integer")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<EsProduct>> search(@RequestBody  EsProductQuery esProductQuery) {
        Page<EsProduct> esProductPage = esProductService.search(esProductQuery);

        return CommonResult.success(CommonPage.restPage(esProductPage));
    }

//    @ApiOperation(value = "根据商品id推荐商品")
//    @RequestMapping(value = "/recommend/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<CommonPage<EsProduct>> recommend(@PathVariable Long id,
//                                                         @RequestParam(required = false, defaultValue = "0") Integer pageNum,
//                                                         @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
//        Page<EsProduct> esProductPage = esProductService.recommend(id, pageNum, pageSize);
//        return CommonResult.success(CommonPage.restPage(esProductPage));
//    }

    @ApiOperation(value = "获取搜索的相关品牌、分类及筛选属性")
    @RequestMapping(value = "/search/relate", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<EsProductRelatedInfo> searchRelatedInfo(@RequestParam(required = false) String keyword) {
        EsProductRelatedInfo productRelatedInfo = esProductService.searchRelatedInfo(keyword);
        return CommonResult.success(productRelatedInfo);
    }

    @ApiOperation(value = "Elastisearch 搜索时间")
    @RequestMapping(value = "/search/time", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> searchTime(@RequestBody  EsProductQuery esProductQuery) {
        long startTime=System.currentTimeMillis();
        Page<EsProduct> esProductPage = esProductService.search(esProductQuery);
        long endTime=System.currentTimeMillis();
        String str  ="Elasticsearch程序运行时间:"+(endTime-startTime)+"ms";
//        Map<String,String> map  = new HashMap() ;
//
//        map.put("程序运行时间:"+str);

        return CommonResult.success(str);
    }


}
