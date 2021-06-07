package com.springboot.cloud.service.impl;

import com.springboot.cloud.dao.MallCategoryGrandMapper;
import com.springboot.cloud.dao.MallGrandMapper;
import com.springboot.cloud.dto.GrandAndCateGrandId;
import com.springboot.cloud.entity.MallCategoryGrand;
import com.springboot.cloud.entity.MallGrand;
import com.springboot.cloud.service.MallCategoryGrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: lirisheng
 * @Date: 2021/2/18 16:36
 * @Version 1.0
 */
@Service
public class MallCategoryGrandServiceImpl  implements MallCategoryGrandService {

    @Autowired
    MallCategoryGrandMapper mallCategoryGrandMapper;

    @Autowired
    MallGrandMapper mallGrandMapper;
    @Override
    public int createList(List<MallCategoryGrand> mallCategoryGrands) {
        return  mallCategoryGrandMapper.insertAll(mallCategoryGrands);
    }

    @Override
    public int create(MallCategoryGrand mallCategoryGrand) {
        return  mallCategoryGrandMapper.insert(mallCategoryGrand);
    }

    @Override
    public int deleteByCategoryId(Long categoryId) {
        return mallCategoryGrandMapper.deleteByCategoryId(categoryId);
    }

    @Override
    public  List<GrandAndCateGrandId> selectByCategoryId(Long categoryId) {
//        Iterator<Long> iterator=mallCategoryGrandMapper.selectByCategoryId(categoryId).stream().map(MallCategoryGrand::getGrandId).collect(Collectors.toList()).iterator();
//        StringBuilder grands=new StringBuilder("");
//        while(iterator.hasNext()){
//            grands.append(mallGrandMapper.selectGrandNameByPrimaryKey(iterator.next()));
//        }
        Iterator<Long> grandId=mallCategoryGrandMapper.selectByCategoryId(categoryId).stream().map(MallCategoryGrand::getGrandId).collect(Collectors.toList()).iterator();
        Iterator<Long> categorygrandId=mallCategoryGrandMapper.selectByCategoryId(categoryId).stream().map(MallCategoryGrand::getCategoryGrandId).collect(Collectors.toList()).iterator();
        List<GrandAndCateGrandId> grands=new ArrayList<>();
        while(grandId.hasNext()){
            MallGrand mallGrand = new MallGrand();
            GrandAndCateGrandId grandAndCateGrandId = new GrandAndCateGrandId();
            Long id = grandId.next();
            mallGrand=mallGrandMapper.selectByPrimaryKey(id);
            grandAndCateGrandId.setCategoryGrandId(categorygrandId.next());
            changeGrandToGrandAndCateGrandId(grandAndCateGrandId,mallGrand);
            grands.add(grandAndCateGrandId);
        }
        return grands;
    }

    void changeGrandToGrandAndCateGrandId(GrandAndCateGrandId grandAndCateGrandId,MallGrand mallGrand){
        grandAndCateGrandId.setGrandId(mallGrand.getGrandId());
        grandAndCateGrandId.setGrandName(mallGrand.getGrandName());
        grandAndCateGrandId.setGrandInfo(mallGrand.getGrandInfo());
        grandAndCateGrandId.setCreateUser(mallGrand.getCreateUser());
        grandAndCateGrandId.setUpdateUser(mallGrand.getUpdateUser());
        grandAndCateGrandId.setCreateTime(mallGrand.getCreateTime());
        grandAndCateGrandId.setUpdateTime(mallGrand.getUpdateTime());
    }


    @Transactional(rollbackForClassName = "Exception.class")
    @Override
    public int updateByCategoryId(Long categoryId,List<MallCategoryGrand> mallCategoryGrands){
        int count=1;
        //删除与分类的所有与品牌之间的关联
        mallCategoryGrandMapper.deleteByCategoryId(categoryId);
        mallCategoryGrands.stream().forEach(a->a.setCategoryId(categoryId));
        //然后在添加相对应的关联关系
        mallCategoryGrandMapper.insertAll(mallCategoryGrands);

        return count;

    }

    @Override
    public int deleteById(Long id) {
        return mallCategoryGrandMapper.deleteByPrimaryKey(id);
    }
}
