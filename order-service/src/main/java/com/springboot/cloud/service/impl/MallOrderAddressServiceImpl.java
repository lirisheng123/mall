package com.springboot.cloud.service.impl;

import com.springboot.cloud.dao.MallOrderAddressMapper;
import com.springboot.cloud.entity.MallOrderAddress;
import com.springboot.cloud.service.MallOrderAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lirisheng
 * @Date: 2021/2/23 11:45
 * @Version 1.0
 */
@Service
public class MallOrderAddressServiceImpl implements MallOrderAddressService {

    @Autowired
    MallOrderAddressMapper mallOrderAddressMapper;

    @Override
    public int updateOrderAdress(Long id,MallOrderAddress mallOrderAddress) {
        mallOrderAddress.setOrderId(id);
        return mallOrderAddressMapper.updateByPrimaryKey(mallOrderAddress);
    }

    @Override
    public int insertOrderAdress(MallOrderAddress mallOrderAddress) {
        return mallOrderAddressMapper.insert(mallOrderAddress);
    }

    @Override
    public int deleteOrderAdress(Long id) {
        return 0;
    }

    @Override
    public MallOrderAddress selectByOrderId(Long orderId) {
        return mallOrderAddressMapper.selectByOrderId(orderId);
    }
}
