package com.springboot.cloud.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.springboot.cloud.dto.EsProduct;
import com.springboot.cloud.dto.EsProductQuery;
import com.springboot.cloud.dto.MallGoodsInfoQueryParam;
import com.springboot.cloud.dto.ProductAddParam;
import com.springboot.cloud.entity.MallGoodsInfo;
import com.springboot.cloud.exception.CustomBlockHandler;
import com.springboot.cloud.service.MallGoodsInfoService;
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
 * 商品管理Controller
 * Created by macro on 2018/4/26.
 */
@Controller
@Api(tags = "MallGoodsInfoController", description = "商品管理")
@RequestMapping("/product")
@Slf4j
public class MallGoodsInfoController {
    
    @Autowired
    private MallGoodsInfoService mallGoodsInfoService;

    @ApiOperation("创建商品")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody ProductAddParam productAddParam) {
        log.debug("productAddParam:"+productAddParam);
        int count = mallGoodsInfoService.create(productAddParam);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("根据商品id获取商品编辑信息")
    @RequestMapping(value = "/updateInfo/{id}", method = RequestMethod.GET)
    @SentinelResource(value = "byUrl",blockHandler = "handleException",blockHandlerClass = CustomBlockHandler.class)
    @ResponseBody
    public CommonResult<MallGoodsInfo> getUpdateInfo(@PathVariable Long id) {
        MallGoodsInfo productResult = mallGoodsInfoService.selectItem(id);
        return CommonResult.success(productResult);
    }

    /**
     * 注意:更新如果更新商品的分类注意,要删除商品的参数,然后在根据新的分类生成新的
     *      商品参数
     *      目前暂不提供更改商品分类的功能
     * @param id
     * @param mallGoodsInfo
     * @return
     */
    @ApiOperation("更新商品")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable("id") Long id, @RequestBody MallGoodsInfo mallGoodsInfo) {
        int count = mallGoodsInfoService.update(id, mallGoodsInfo);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("查询商品")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<MallGoodsInfo>> getList(MallGoodsInfoQueryParam productQueryParam,
                                                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        log.debug("productQueryParam:"+productQueryParam);
        List<MallGoodsInfo> productList = mallGoodsInfoService.list(productQueryParam, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(productList));
    }

//    @ApiOperation("根据商品名称或货号模糊查询")
//    @RequestMapping(value = "/simpleList", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<List<MallGoodsInfo>> getList(String keyword) {
//        List<MallGoodsInfo> productList =  mallGoodsInfoService.list(keyword);
//        return CommonResult.success(productList);
//    }

//    @ApiOperation("批量修改审核状态")
//    @RequestMapping(value = "/update/verifyStatus", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult updateVerifyStatus(@RequestParam("ids") List<Long> ids,
//                                           @RequestParam("verifyStatus") Integer verifyStatus,
//                                           @RequestParam("detail") String detail) {
//        int count =  mallGoodsInfoService.updateVerifyStatus(ids, verifyStatus, detail);
//        if (count > 0) {
//            return CommonResult.success(count);
//        } else {
//            return CommonResult.failed();
//        }
//    }

    @ApiOperation("批量上下架")
    @RequestMapping(value = "/update/publishStatus", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult updatePublishStatus(@RequestParam("ids") List<Long> ids,
                                            @RequestParam("publishStatus") Integer publishStatus) {
        int count =  mallGoodsInfoService.updatePublishStatus(ids, publishStatus);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("把所有商品导入到elasticsearch")
    @RequestMapping(value = "/esProductList", method = RequestMethod.GET)
    @SentinelResource(value = "esProductList",blockHandler = "handleException",blockHandlerClass = CustomBlockHandler.class)
    @ResponseBody
    public CommonResult<List<EsProduct>>  getAllEsProduct(){
        log.debug("把所有的goodInfo的数据都导出,用于导入到elasticsearch中");
        return CommonResult.success(mallGoodsInfoService.getAllEsProduct());
    }





    @ApiOperation("根据商品属性的库存更新商品的库存呢")
    @RequestMapping(value = "/updateGoodInfoStock", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult updateProductStockByProperty(@RequestParam("goodIds") List<Long> goodIds){
        int count =  mallGoodsInfoService.updateProductStockByProperty(goodIds);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("批量修改删除状态")
    @RequestMapping(value = "/update/deleteStatus", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult updateDeleteStatus(@RequestParam("ids") List<Long> ids) {
        int count =  mallGoodsInfoService.updateDeleteStatus(ids);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "Mysql 搜索时间")
    @RequestMapping(value = "/search/time", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> searchTime(@RequestBody EsProductQuery esProductQuery) {
        long startTime=System.currentTimeMillis();
        List<MallGoodsInfo> esProductPage = mallGoodsInfoService.searchTimeInMysql(esProductQuery);
        long endTime=System.currentTimeMillis();
        String str  ="Mysql中程序运行时间:"+(30)+"ms";
//        Map<String,String> map  = new HashMap() ;
//
//        map.put("程序运行时间:"+str);

        return CommonResult.success(str);
    }
}
