package com.springboot.cloud.dao;



import com.springboot.cloud.entity.MallGoodsProperty;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface MallGoodsPropertyMapper {
    int deleteByPrimaryKey(Long goodsPropertyId);

    int insert(MallGoodsProperty record);

    MallGoodsProperty selectByPrimaryKey(Long goodsPropertyId);

    List<MallGoodsProperty> selectAll();

    @Select("SELECT  * FROM  mall_goods_property WHERE  goods_id=#{goodsId} ")
    List<MallGoodsProperty> selectByGoodsId(Long goodsId);

    int updateByPrimaryKey(MallGoodsProperty record);

    int insertAll(List<MallGoodsProperty> mallGoodsProperties);

    @Update("UPDATE  mall_goods_property SET stock_num=stock_num-#{count},selled_stock_num=selled_stock_num+#{count}" +
            " WHERE  goods_property_id=#{id} ")
    int updateSkuById(@Param("id") Long id,@Param("count") Integer count);

    @Update("UPDATE  mall_goods_property SET stock_num=stock_num+#{count},selled_stock_num=selled_stock_num-#{count}" +
            " WHERE  goods_property_id=#{id} ")
    int decreateSkuById(@Param("id") Long id,@Param("count") Integer count);

    @Delete("DELETE FROM  mall_goods_property WHERE  goods_id=#{goodId} ")
    int deleteByGoodId(Long goodId);



}