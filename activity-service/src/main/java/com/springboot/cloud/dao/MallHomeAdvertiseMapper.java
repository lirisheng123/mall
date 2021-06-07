package com.springboot.cloud.dao;

import com.springboot.cloud.dto.AdvertiesParams;
import com.springboot.cloud.entity.MallHomeAdvertise;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface MallHomeAdvertiseMapper {
    int deleteByPrimaryKey(Long mallAdvertiseId);

    int insert(MallHomeAdvertise record);

    MallHomeAdvertise selectByPrimaryKey(Long mallAdvertiseId);

    List<MallHomeAdvertise> selectAll();

    int updateByPrimaryKey(MallHomeAdvertise record);

    List<MallHomeAdvertise> selectList(AdvertiesParams advertiesParams);

    int deleteList(List<Long> ids);

    @Update("UPDATE  mall_home_advertise SET  status=#{status} " +
            "WHERE  mall_advertise_id=#{id} ")
    int updateStatueById(@Param("id") Long id, @Param("status")Integer status);

}