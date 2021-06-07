package com.springboot.cloud.service;

import com.springboot.cloud.dto.FlashPromotionParams;
import com.springboot.cloud.dto.SecondParams;
import com.springboot.cloud.entity.MallFlashPromotionSession;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/3/30 0:56
 * @Version 1.0
 */
public interface MallFlashPromotionSessionService {

    /**
     * 添加秒杀单
     */
    int create(SecondParams secondParams);

    /**
     * 批量删除秒杀单
     */
     int deleteList(List<Long> ids);

    /**
     * 更改秒杀单
     */
     int update(Long id,MallFlashPromotionSession mallFlashPromotionSession);
    /**
     * 多参数分页查询秒杀单
     */
     List<MallFlashPromotionSession> selectList(FlashPromotionParams flashPromotionParams,
                                                Integer pageSize,Integer pageNum);
    /**
     *更改状态
     */
//    int updateStatus(Long id,Integer status);

    /**
     * 根据id查找
     */
    MallFlashPromotionSession select(Long id);


    /**
     * 更改秒杀单的缓存
     */
    int updateStatusById(Long id,Integer status);
}
