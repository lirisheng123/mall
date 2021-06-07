package com.springboot.cloud.service.productService;


import com.springboot.cloud.common.dto.CategoryGrand;
import com.springboot.cloud.common.entity.MallGoodsCategory;
import com.springboot.cloud.common.util.CommonPage;
import com.springboot.cloud.common.util.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 商品分类管理Service
 * Created by macro on 2018/4/26.
 */

@FeignClient(contextId ="GoodsCategory" ,value = "product-service")
@RequestMapping("/goodsCategory")
public interface MallGoodsCategoryService {
    /**
     * 创建商品分类
     */
    @PostMapping("/create")
    CommonResult create(@Validated @RequestBody CategoryGrand categoryGrand) ;

    /**
     * 修改商品分类
     * 注意 这里规定,分类的级别是不可以进行修改
     *              一级分类的parentId只能为0
     *
     * @param id
     * @param mallGoodsCategory
     * @return
     */
    @PostMapping("/update/{id}")
    CommonResult update(@PathVariable("id") Long id,
                        @RequestBody MallGoodsCategory mallGoodsCategory);

    /**
     * 分页查询商品分类
     */
    @GetMapping("/list/{parentId}")
    CommonResult<CommonPage<MallGoodsCategory>> getList(@PathVariable("parentId") Long parentId,
                                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum);
    /**
     * 删除商品分类
     * 这里目前只提供二级分类的删除,暂不提供一级分类删除
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    CommonResult delete(@PathVariable("id") Long id);


}
