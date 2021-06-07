package com.springboot.cloud.service.impl;

import com.github.pagehelper.PageHelper;

import com.springboot.cloud.dao.MallGoodsCategoryMapper;
import com.springboot.cloud.dao.MallGoodsInfoMapper;
import com.springboot.cloud.dto.CategoryGrand;
import com.springboot.cloud.dto.CategoryParentAndChird;
import com.springboot.cloud.entity.MallCategoryGrand;
import com.springboot.cloud.entity.MallGoodsCategory;
import com.springboot.cloud.entity.MallGoodsInfo;
import com.springboot.cloud.service.MallCategoryGrandService;
import com.springboot.cloud.service.MallGoodsCategoryService;
import com.springboot.cloud.service.MallCategoryParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 商品分类管理Service实现类
 * Created by macro on 2018/4/26.
 */
@Service
public class MallGoodsCategoryServiceImpl implements MallGoodsCategoryService {
    @Autowired
    private MallGoodsCategoryMapper mallGoodsCategoryMapper;
    @Autowired
    private MallCategoryGrandService mallCategoryGrandService;

    @Autowired
    private MallCategoryParamService mallCategoryParamService;

    @Autowired
    private MallGoodsInfoMapper mallGoodsInfoMapper;

    @Override
    @Transactional(rollbackForClassName = "Exception.class")
    public int create(CategoryGrand categoryGrand) {
        MallGoodsCategory mallGoodsCategory = categoryGrand;
        int count = mallGoodsCategoryMapper.insert(mallGoodsCategory);
        //设置相关联的品牌参数
//        Long categoryId = mallGoodsCategory.getCategoryId();
//        Iterator<Long> grandsId = categoryGrand.getGrandsId().iterator();
//        List<MallCategoryGrand> mallCategoryGrands = new ArrayList();
//        while(grandsId.hasNext()){
//            MallCategoryGrand  mallCategoryGrand = new MallCategoryGrand();
//            mallCategoryGrand.setCategoryId(categoryId);
//            mallCategoryGrand.setCreateUser(mallGoodsCategory.getCreateUser());
//            mallCategoryGrand.setUpdateUser(mallGoodsCategory.getUpdateUser());
//            mallCategoryGrand.setGrandId((Long)grandsId.next());
//            mallCategoryGrands.add(mallCategoryGrand);
//        }
//
//        mallCategoryGrandService.createList(mallCategoryGrands);
        return count;
    }



//    /**
//     * 批量插入商品分类与筛选属性关系表
//     * @param productCategoryId 商品分类id
//     * @param productAttributeIdList 相关商品筛选属性id集合
//     */
//    private void insertRelationList(Long productCategoryId, List<Long> productAttributeIdList) {
//        List<PmsProductCategoryAttributeRelation> relationList = new ArrayList<>();
//        for (Long productAttrId : productAttributeIdList) {
//            PmsProductCategoryAttributeRelation relation = new PmsProductCategoryAttributeRelation();
//            relation.setProductAttributeId(productAttrId);
//            relation.setProductCategoryId(productCategoryId);
//            relationList.add(relation);
//        }
//        productCategoryAttributeRelationDao.insertList(relationList);
//    }

    @Override
    public int update(Long id, MallGoodsCategory mallGoodsCategory) {
        mallGoodsCategory.setCategoryId(id);
        return mallGoodsCategoryMapper.updateByPrimaryKey(mallGoodsCategory);
    }

    @Override
    public List<MallGoodsCategory> getList(Long parentId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return mallGoodsCategoryMapper.selectByLevel(parentId);
    }

    @Transactional(rollbackForClassName = "Exception.class")
    @Override
    public int delete(Long id) {
        int count ;
        //检查商品是否有用到此分类,有,则删除失败
//        if(mallGoodsInfoMapper.selectByCategoryId(id)!=null){
//            return 0;
//        }

        //删除二级分类

        //删除分类与参数记录记录
        mallCategoryParamService.deleteByCategoryId(id);
        //删除分类与品牌的关联记录
        mallCategoryGrandService.deleteByCategoryId(id);
        //删除分类记录
        count= mallGoodsCategoryMapper.deleteByPrimaryKey(id);
        return count;
    }

    @Override
    public MallGoodsCategory getItem(Long id) {
        return  mallGoodsCategoryMapper.selectByPrimaryKey(id);
    }

    /**
     * 1 可以进行删除,0不可以进行删除
     * @param id
     * @return
     */
    @Override
    public int judgeDelete(Long id) {
        MallGoodsCategory mallGoodsCategory = mallGoodsCategoryMapper.selectByPrimaryKey(id);
        if(mallGoodsCategory.getParentId()==0){
            //判断是否是否存在二级依赖,没有,则进行

            if(mallGoodsCategoryMapper.selectParentById( id)==null){
                //进行删除,删除一级依赖,二级依赖,二级所有的品牌和属性和参数
                return 1;
            }
            return 0;
        }

        if(mallGoodsInfoMapper.selectByCategoryId(mallGoodsCategory.getCategoryId())==null){
            //删除二级依赖,二级所有的品牌,属性和参数
            return 1;
        }
        //进行二级菜单的删除
        return 0;
    }

    @Override
    public List<CategoryParentAndChird> listWithChildren() {
        return mallGoodsCategoryMapper.listWithChildren();
    }

    //    @Override
//    public PmsProductCategory getItem(Long id) {
//        return productCategoryMapper.selectByPrimaryKey(id);
//    }
//
//    @Override
//    public int updateNavStatus(List<Long> ids, Integer navStatus) {
//        PmsProductCategory productCategory = new PmsProductCategory();
//        productCategory.setNavStatus(navStatus);
//        PmsProductCategoryExample example = new PmsProductCategoryExample();
//        example.createCriteria().andIdIn(ids);
//        return productCategoryMapper.updateByExampleSelective(productCategory, example);
//    }
//
//    @Override
//    public int updateShowStatus(List<Long> ids, Integer showStatus) {
//        PmsProductCategory productCategory = new PmsProductCategory();
//        productCategory.setShowStatus(showStatus);
//        PmsProductCategoryExample example = new PmsProductCategoryExample();
//        example.createCriteria().andIdIn(ids);
//        return productCategoryMapper.updateByExampleSelective(productCategory, example);
//    }
//
//    @Override
//    public List<PmsProductCategoryWithChildrenItem> listWithChildren() {
//        return productCategoryDao.listWithChildren();
//    }

    /**
     * 根据分类的parentId设置分类的level
     */
//    private void setCategoryLevel(PmsProductCategory productCategory) {
//        //没有父分类时为一级分类
//        if (productCategory.getParentId() == 0) {
//            productCategory.setLevel(0);
//        } else {
//            //有父分类时选择根据父分类level设置
//            PmsProductCategory parentCategory = productCategoryMapper.selectByPrimaryKey(productCategory.getParentId());
//            if (parentCategory != null) {
//                productCategory.setLevel(parentCategory.getLevel() + 1);
//            } else {
//                productCategory.setLevel(0);
//            }
//        }
//    }
}
