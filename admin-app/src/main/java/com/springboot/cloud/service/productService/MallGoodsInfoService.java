package com.springboot.cloud.service.productService;


import com.springboot.cloud.common.dto.MallGoods;
import com.springboot.cloud.common.dto.MallGoodsInfoQueryParam;
import com.springboot.cloud.common.entity.MallGoodsInfo;
import com.springboot.cloud.common.util.CommonPage;
import com.springboot.cloud.common.util.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品管理Service
 * Created by macro on 2018/4/26.
 */

@FeignClient(contextId ="GoodsInfo" ,value = "product-service")
@RequestMapping("/product")
public interface MallGoodsInfoService {
    /**
     * 创建商品
     */
    @PostMapping("/create")
    CommonResult create(@RequestBody MallGoods mallGoods);

    /**
     * 根据商品编号获取更新信息
     */
    @PostMapping("/updateInfo/{id}")
    CommonResult<MallGoodsInfo> getUpdateInfo(@PathVariable("id") Long id) ;

    /**
     * 更新商品
     * 注意:更新如果更新商品的分类注意,要删除商品的参数,然后在根据新的分类生成新的
     *      商品参数
     *      目前暂不提供更改商品分类的功能
     * @param id
     * @param mallGoodsInfo
     * @return
     */
    @PostMapping("/update/{id}")
    CommonResult update(@PathVariable("id") Long id, @RequestBody MallGoodsInfo mallGoodsInfo);

    /**
     * 查询商品
     */
    @GetMapping("/list")
    CommonResult<CommonPage<MallGoodsInfo>> getList(MallGoodsInfoQueryParam productQueryParam,
                                                    @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum);


    /**
     * 批量上下架
     */
    @PostMapping("/update/publishStatus")
    CommonResult updatePublishStatus(@RequestParam("ids") List<Long> ids,
                                     @RequestParam("publishStatus") Integer publishStatus);

}
