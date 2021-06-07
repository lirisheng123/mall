package com.springboot.cloud.service;

import com.springboot.cloud.common.entity.MallGoodsProperty;
import com.springboot.cloud.dto.MallRecommend;
import com.springboot.cloud.util.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/3/27 20:30
 * @Version 1.0
 */
@FeignClient(contextId ="GoodsProperty" ,value = "product-service")
public interface ProductService {

    /**
     * 添加分类与参数的关联关系添加商品属性
     */
    @PostMapping("/goodsProperty/create")
    CommonResult create(@RequestBody MallGoodsProperty mallGoodsProperty);


    /**
     * 根据id修改商品属性
     */
    @PostMapping("/goodsProperty/update/{id}")
    CommonResult update(@PathVariable("id") Long id, @RequestBody  MallGoodsProperty mallGoodsProperty);


    /**
     * 获取id获取单个商品属性信息
     */
    @GetMapping("/goodsProperty/item/{id}")
    CommonResult<MallGoodsProperty>  getItem(@PathVariable("id") Long id);

    /**
     * 根据id删除商品属性
     */
    @GetMapping("/goodsProperty/deleteItem/{id}")
    CommonResult delete( @PathVariable("id") Long id);


    /**
     * 根据商品id查找商品属性
     */
    @GetMapping("/goodsProperty/list/{id}")
    CommonResult<List<MallGoodsProperty>> selectByGoodsId(@PathVariable("id")Long goodsId);


    /**
     * 根据商品属性id来修改相应的库存呢
     */
    @RequestMapping(value = "/goodsProperty/updateCount/{id}", method = RequestMethod.GET)
    CommonResult updateSkuById(@PathVariable("id") Long id,@RequestParam("count") Integer count);


    /**
     * 据商品属性的库存更新商品的库存
     * @param goodIds
     * @return
     */

    @RequestMapping(value = "/product/updateGoodInfoStock", method = RequestMethod.GET)
    CommonResult updateProductStockByProperty(@RequestParam("goodIds") List<Long> goodIds);


    /**
     * 根据id来减少商品属性的库存
     */
    @RequestMapping(value = "/goodsProperty/decreaseSkuById/{id}", method = RequestMethod.GET)
    CommonResult  decreaseSkuById(@PathVariable("id")Long id,@RequestParam("count") Integer count);

    /**
     *添加推荐的记录
     */

    @RequestMapping(value = "/recommend/create", method = RequestMethod.POST)
    CommonResult create(@RequestBody MallRecommend mallRecommend) ;


    /**
     * 根据用户id和商品id获取推荐记录
     */
    @RequestMapping(value = "/recommend/select", method = RequestMethod.GET)
     CommonResult<MallRecommend>   selectByUserIdAndGoodId(@RequestParam("userId") Long userId,@RequestParam("goodId") Long goodId);



    /**
     * 更改推荐记录
     */
    @RequestMapping(value = "/recommend/update/{id}", method = RequestMethod.POST)
   CommonResult  update(@PathVariable("id")Long id,@RequestBody MallRecommend mallRecommend);

}
