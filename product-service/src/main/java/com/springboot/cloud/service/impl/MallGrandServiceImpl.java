package com.springboot.cloud.service.impl;

import com.github.pagehelper.PageHelper;

import com.springboot.cloud.dao.MallCategoryGrandMapper;
import com.springboot.cloud.dao.MallGrandMapper;
import com.springboot.cloud.dto.MallGrandQueryParam;
import com.springboot.cloud.entity.MallGrand;
import com.springboot.cloud.service.MallGrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

/**
 * 商品品牌管理Service实现类
 * Created by macro on 2018/4/26.
 */
@Service
public class MallGrandServiceImpl implements MallGrandService {

    @Autowired
    private MallGrandMapper mallGrandMapper;

    @Autowired
    private MallCategoryGrandMapper mallCategoryGrandMapper;

    @Override
    public List<MallGrand> listAllBrand(Integer pageSize,Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return mallGrandMapper.selectAll();
    }

    @Override
    public int createBrand(MallGrand mallGrand)
    {
        return mallGrandMapper.insert(mallGrand);
    }

    @Override
    public int updateBrand(Long grandId,MallGrand mallGrand) {
        mallGrand.setGrandId(grandId);

        return mallGrandMapper.updateByPrimaryKey(mallGrand);
    }

    @Override
    public int deleteBrand(Long id) {
        //检查是否有分类使用到该品牌
        if(mallCategoryGrandMapper.selectByGrandId(id)!=null){
            return 0;
        }
        //如果没有,才可以进行删除操作
        return mallGrandMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int deleteBrand(List<Long> ids) {

        Iterator<Long> iterator = ids.iterator();

        try {
            while (iterator.hasNext()) {
                mallGrandMapper.deleteByPrimaryKey((Long) iterator.next());
            }
        }catch (Exception exception){
            return 0;
        }
        return  1;
    }

    @Override
    public List<MallGrand> listBrand(MallGrandQueryParam mallGrandQueryParam, int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        return mallGrandMapper.selectByNamePage(mallGrandQueryParam);
    }

    @Override
    public MallGrand getBrand(Long id) {
        return mallGrandMapper.selectByPrimaryKey(id);
    }
}
