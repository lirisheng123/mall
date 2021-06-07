package com.springboot.cloud.dao;


import com.springboot.cloud.entity.MallOrderOperateHistory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MallOrderOperateHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MallOrderOperateHistory record);

    MallOrderOperateHistory selectByPrimaryKey(Long id);

    List<MallOrderOperateHistory> selectAll();

    int updateByPrimaryKey(MallOrderOperateHistory record);
}