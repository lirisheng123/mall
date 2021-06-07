package com.springboot.cloud.service;



import com.springboot.cloud.entity.MallGoodsProperty;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品属性管理Service
 * Created by macro on 2018/4/26.
 */
public interface MallGoodsPropertyService {
    /**
     * 根据商品id分页获取商品属性
     * @param cid 分类id
     * @param type 0->属性；2->参数
     */
    List<MallGoodsProperty> getList(Long cid, Integer type, Integer pageSize, Integer pageNum);

    /**
     * 添加商品属性
     */
    @Transactional
    int create(MallGoodsProperty pmsProductAttributeParam);

    /**
     * 根据id修改商品属性
     */
    int update(Long id, MallGoodsProperty productAttributeParam);

    /**
     * 获取单个商品属性信息
     */
    MallGoodsProperty getItem(Long id);


    /**
     * 根据id删除商品属性
     */
    int delete(Long ids);

    /**
     * 根据商品id查找商品属性
     */
    List<MallGoodsProperty> selectByGoodsId(Long goodsId);

    /**
     * 批量修改商品的属性
     */
    int updateList(List<MallGoodsProperty> mallGoodsProperties);


    /**
     * 根据商品属性id来修改相应的库存呢
     */
    int updateSkuById(Long id,Integer count);

    /**
     * 根据id来减少商品属性的库存
     */
    int  decreaseSkuById(Long id,Integer count);

}
