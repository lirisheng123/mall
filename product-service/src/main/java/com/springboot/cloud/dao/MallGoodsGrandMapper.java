package com.springboot.cloud.dao;


import com.springboot.cloud.entity.MallGoodsGrand;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MallGoodsGrandMapper {
    int deleteByPrimaryKey(Long goodsGrandId);

    int insert(MallGoodsGrand record);

    MallGoodsGrand selectByPrimaryKey(Long goodsGrandId);

    List<MallGoodsGrand> selectAll();

    int updateByPrimaryKey(MallGoodsGrand record);

    @Select("SELECT  * FROM  mall_goods_grand WHERE  goods_id=#{goodId} ")
    MallGoodsGrand selectGrandByGoodId(Long goodId);

    @Delete("DELETE FROM  mall_goods_grand WHERE  goods_id=#{goodId} ")
    int deleteByGoodId(Long goodId);
}