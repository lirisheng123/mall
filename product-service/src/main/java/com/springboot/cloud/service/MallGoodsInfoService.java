package com.springboot.cloud.service;


import com.springboot.cloud.dto.*;
import com.springboot.cloud.entity.MallGoodsInfo;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品管理Service
 * Created by macro on 2018/4/26.
 */
public interface MallGoodsInfoService {
    /**
     * 创建商品
     */
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    int create(ProductAddParam productAddParam);

    /**
     * 根据商品编号获取更新信息
     */
    MallGoodsInfo selectItem(Long id);

    /**
     * 更新商品
     */
    int update(Long id, MallGoodsInfo mallGoodsInfo);



    /**
     * 分页查询商品
     */
    List<MallGoodsInfo> list(MallGoodsInfoQueryParam productQueryParam, Integer pageSize, Integer pageNum);

    /**
     * 把所有商品导入到elasticsearch
     */
    List<EsProduct>  getAllEsProduct();


    /**
     * 根据商品属性的库存更新商品的库存呢
     */
    int updateProductStockByProperty(List<Long> goodIds);
    /**
     * 批量修改审核状态
     * @param ids 产品id
     * @param verifyStatus 审核状态
     * @param detail 审核详情
     */
//    @Transactional
//    int updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail);

    /**
     * 批量修改商品上架状态
     */
    int updatePublishStatus(List<Long> ids, Integer publishStatus);

    /**
     * 根据goodId查询goodAddParam
     */
     ProductAddParam getProductAddParamByGoodId(Long goodId);

    /**
     * 批量修改商品推荐状态
     */
//    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    /**
     * 批量修改新品状态
     */
//    int updateNewStatus(List<Long> ids, Integer newStatus);

    /**
     * 批量删除商品
     */
    int updateDeleteStatus(List<Long> ids);

    /**
     * 根据商品名称或者货号模糊查询
     */
//    List<MallGoodsInfo> list(String keyword);

    /**
     * 搜索时间
     */
    List<MallGoodsInfo>  searchTimeInMysql(EsProductQuery esProductQuery);
}
