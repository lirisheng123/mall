package com.springboot.cloud.dao;



import com.springboot.cloud.entity.MallGoodsParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MallGoodsParamMapper {
    int deleteByPrimaryKey(Long goodsParamId);

    int insert(MallGoodsParam record);

    int insertList(List<MallGoodsParam> mallGoodsParams);

    MallGoodsParam selectByPrimaryKey(Long goodsParamId);

    @Select("SELECT * FROM  mall_goods_param WHERE  goods_id = #{goodsId} ")
    @ResultType(value = MallGoodsParam.class)
    List<MallGoodsParam> selectByGoodId(Long goodsId);

    List<MallGoodsParam> selectAll();

    int updateByPrimaryKey(MallGoodsParam record);

    @Delete("DELETE FROM  mall_goods_param WHERE  goods_id=#{goodId} ")
    int deleteByGoodId(Long goodId);
}