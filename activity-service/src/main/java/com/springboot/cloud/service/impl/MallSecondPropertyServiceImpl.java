package com.springboot.cloud.service.impl;

import com.springboot.cloud.dao.MallSecondPropertyMapper;
import com.springboot.cloud.entity.MallSecondProperty;
import com.springboot.cloud.service.MallSecondPropertyService;
import com.springboot.cloud.service.SecondService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/4/25 15:55
 * @Version 1.0
 */
@Service
@Slf4j
public class MallSecondPropertyServiceImpl implements MallSecondPropertyService {

    @Autowired
    MallSecondPropertyMapper mallSecondPropertyMapper;

    @Autowired
    SecondService secondService;

    @Override
    public int insert(MallSecondProperty mallSecondProperty) {
        return mallSecondPropertyMapper.insert(mallSecondProperty);
    }

    @Override
    public int update(Long id, MallSecondProperty mallSecondProperty) {
        mallSecondProperty.setMallSecondPropertyId(id);
        return mallSecondPropertyMapper.updateByPrimaryKey(mallSecondProperty) ;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateList(List<MallSecondProperty> mallSecondProperties) {
        //记得在前端进行判断是否可以更改
        Iterator iterator = mallSecondProperties.iterator();
        while (iterator.hasNext()){
            mallSecondPropertyMapper.updateByPrimaryKey((MallSecondProperty) iterator.next());
        }

        return 1;
    }

    @Override
    public List<MallSecondProperty> selectByFlashId(Long flashId) {
        return  mallSecondPropertyMapper.selectByFlashId(flashId);
    }

    @Override
    public int deleteList(List<Long> ids) {
        ids.stream().forEach(item->{
            mallSecondPropertyMapper.deleteByFlashId(item);
        });
        return 0;
    }
}
