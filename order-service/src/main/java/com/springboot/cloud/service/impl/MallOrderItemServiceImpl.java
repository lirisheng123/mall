package com.springboot.cloud.service.impl;

import com.springboot.cloud.dao.MallOrderItemMapper;
import com.springboot.cloud.entity.MallOrderItem;
import com.springboot.cloud.service.MallOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/23 11:46
 * @Version 1.0
 */
@Service
public class MallOrderItemServiceImpl implements MallOrderItemService {

    @Autowired
    MallOrderItemMapper mallOrderItemMapper;

    @Override
    public List<MallOrderItem> selectByOrderId(Long orderId) {
        return  mallOrderItemMapper.selectByOrderId( orderId) ;
    }

    @Override
    public MallOrderItem select(Long id) {
        return mallOrderItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertList(List<MallOrderItem> mallOrderItems) {
        return mallOrderItemMapper.insertList( mallOrderItems);
    }

    @Override
    public int deleteList(List<Long> id) {
        return 0;
    }
}
