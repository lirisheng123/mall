package com.springboot.cloud.controller.productController;


import com.springboot.cloud.common.dto.MallGoods;
import com.springboot.cloud.common.dto.MallGoodsInfoQueryParam;
import com.springboot.cloud.common.entity.MallGoodsInfo;
import com.springboot.cloud.common.util.CommonPage;
import com.springboot.cloud.common.util.CommonResult;
import com.springboot.cloud.service.productService.MallGoodsInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品管理Controller
 * Created by macro on 2018/4/26.
 */
@Controller
@Api(tags = "MallGoodsInfoController", description = "商品管理")
@RequestMapping("/admin/product")
public class MallGoodsInfoController {
    
    @Autowired
//    @Qualifier("mallGoodsInfoService")
    private MallGoodsInfoService mallGoodsInfoService;

    @ApiOperation("创建商品")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody MallGoods mallGoods) {
        return  mallGoodsInfoService.create(mallGoods);
    }

    @ApiOperation("根据商品id获取商品编辑信息")
    @RequestMapping(value = "/updateInfo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<MallGoodsInfo> getUpdateInfo(@PathVariable Long id) {
        return  mallGoodsInfoService.getUpdateInfo(id);
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
    public CommonResult update(@PathVariable Long id, @RequestBody MallGoodsInfo mallGoodsInfo) {
      return  mallGoodsInfoService.update(id,mallGoodsInfo);
    }

    @ApiOperation("查询商品")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<MallGoodsInfo>> getList(MallGoodsInfoQueryParam productQueryParam,
                                                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
       return  mallGoodsInfoService.getList(productQueryParam,pageSize,pageNum);
    }



    @ApiOperation("批量上下架")
    @RequestMapping(value = "/update/publishStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePublishStatus(@RequestParam("ids") List<Long> ids,
                                            @RequestParam("publishStatus") Integer publishStatus) {
       return  mallGoodsInfoService.updatePublishStatus(ids,publishStatus);
    }


}
