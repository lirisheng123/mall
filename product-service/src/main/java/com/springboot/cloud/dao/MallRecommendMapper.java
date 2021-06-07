package com.springboot.cloud.dao;


import com.springboot.cloud.entity.MallRecommend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MallRecommendMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MallRecommend record);

    MallRecommend selectByPrimaryKey(Long id);

    List<MallRecommend> selectAll();

    int updateByPrimaryKey(MallRecommend record);

    @Select("SELECT  * FROM mall_recommend WHERE  user_id=#{userId} AND  good_id=#{gooodId} ")
    MallRecommend selectByUserIdAndGoodId(@Param("userId") Long userId,@Param("gooodId") Long goodId) ;
}