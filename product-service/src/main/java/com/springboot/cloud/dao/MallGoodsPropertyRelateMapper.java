package com.springboot.cloud.dao;

import com.springboot.cloud.entity.MallGoodsPropertyRelate;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface MallGoodsPropertyRelateMapper {
    int deleteByPrimaryKey(Long goodsPropertyRelateId);

    int insert(MallGoodsPropertyRelate record);

    MallGoodsPropertyRelate selectByPrimaryKey(Long goodsPropertyRelateId);

    List<MallGoodsPropertyRelate> selectAll();

    int updateByPrimaryKey(MallGoodsPropertyRelate record);

    int insertAll(List<MallGoodsPropertyRelate> mallGoodsPropertyRelates);

    @Select("SELECT  * FROM  mall_goods_property_relate WHERE  goods_id=#{goodId} ")
    List<MallGoodsPropertyRelate> selectByGoodId(Long goodId);

    @Delete("DELETE FROM mall_goods_property_relate  WHERE  goods_id=#{goodId} ")
    int deleteByGoodId(Long goodId);
}