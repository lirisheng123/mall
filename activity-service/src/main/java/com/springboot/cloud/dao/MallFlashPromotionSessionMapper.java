package com.springboot.cloud.dao;


import com.springboot.cloud.dto.FlashPromotionParams;
import com.springboot.cloud.entity.MallFlashPromotionSession;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MallFlashPromotionSessionMapper {
    int deleteByPrimaryKey(Long mallFlashPromotionId);

    int insert(MallFlashPromotionSession record);

    MallFlashPromotionSession selectByPrimaryKey(Long mallFlashPromotionId);

    List<MallFlashPromotionSession> selectAll();

    int updateByPrimaryKey(MallFlashPromotionSession record);

    int deleteList(List<Long> ids);

    List<MallFlashPromotionSession> selectList(FlashPromotionParams flashPromotionParams);

    @Update("UPDATE  mall_flash_promotion_session SET  status=#{status} " +
            "WHERE  mall_flash_promotion_id=#{id} ")
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);

}