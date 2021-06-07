package com.springboot.cloud.service.impl;


import com.springboot.cloud.dao.MallGoodsPropertyMapper;
import com.springboot.cloud.entity.MallGoodsInfo;
import com.springboot.cloud.entity.MallGoodsProperty;
import com.springboot.cloud.service.MallGoodsInfoService;
import com.springboot.cloud.service.MallGoodsPropertyService;
import com.springboot.cloud.service.MallGrandService;
import io.swagger.models.auth.In;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

/**
 * 商品属性管理Service实现类
 * Created by macro on 2018/4/26.
 */
@Service
public class MallGoodsPropertyServiceImpl implements MallGoodsPropertyService {

    @Autowired
    MallGoodsPropertyMapper mallGoodsPropertyMapper;


    @Override
    public List<MallGoodsProperty> getList(Long cid, Integer type, Integer pageSize, Integer pageNum) {
        return null;
    }

    @Override
    public int create(MallGoodsProperty mallGoodsProperty) {
        return mallGoodsPropertyMapper.insert(mallGoodsProperty);
    }

    @Override
    public int update(Long id, MallGoodsProperty mallGoodsProperty) {
        mallGoodsProperty.setGoodsPropertyId(id);
        return mallGoodsPropertyMapper.updateByPrimaryKey(mallGoodsProperty);
    }

    @Override
    public MallGoodsProperty getItem(Long id) {
        return mallGoodsPropertyMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(Long ids) {
        return mallGoodsPropertyMapper.deleteByPrimaryKey(ids);
    }

    @Override
    public List<MallGoodsProperty> selectByGoodsId(Long goodsId) {
        return mallGoodsPropertyMapper.selectByGoodsId(goodsId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateList(List<MallGoodsProperty> mallGoodsProperties) {

        Iterator<MallGoodsProperty> iterator = mallGoodsProperties.iterator();
        while(iterator.hasNext()){
            mallGoodsPropertyMapper.updateByPrimaryKey(iterator.next());
        }
        return 1;
    }

    @Override
    public int updateSkuById(Long id, Integer count) {
        return mallGoodsPropertyMapper.updateSkuById(id,count);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int decreaseSkuById(Long id, Integer count) {

        return mallGoodsPropertyMapper.decreateSkuById(id,count);

    }
}
