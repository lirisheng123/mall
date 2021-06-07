package com.springboot.cloud.dao;



import com.springboot.cloud.dto.EsProductQuery;
import com.springboot.cloud.dto.MallGoods;
import com.springboot.cloud.dto.MallGoodsInfoQueryParam;
import com.springboot.cloud.entity.MallGoodsInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface MallGoodsInfoMapper {
    int deleteByPrimaryKey(Long goodsId);

    int insert(MallGoodsInfo record);

    MallGoodsInfo selectByPrimaryKey(Long goodsId);

    @Select("SELECT  goods_id FROM  mall_goods_info WHERE  goods_category_id=#{categoryId} ")
    Long selectByCategoryId(Long categoryId);

    List<MallGoodsInfo> selectAll();

    int updateByPrimaryKey(MallGoodsInfo record);

    int updateSellStatueByGoodsId(Map<String, Object>  maps);

    List<MallGoodsInfo> selectPageByManyCondition(MallGoodsInfoQueryParam mallGoodsInfoQueryParam);

    @Update("UPDATE  mall_goods_info SET  stock_num=#{stock} ,selled_stock_num=#{selledStock} WHERE goods_id=#{id}   ")
    int updateStockById(@Param("id")Long id,@Param("stock")Integer stock,@Param("selledStock")Integer selledStock);

    List<MallGoodsInfo>  searchTime(EsProductQuery esProductQuery);
//    int deleteList(Map<String,Object> map);

}