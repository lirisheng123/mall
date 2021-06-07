package com.springboot.cloud.service.impl;

import com.github.pagehelper.PageHelper;
import com.springboot.cloud.dao.MallGoodsInfoMapper;
import com.springboot.cloud.dao.MallGoodsPropertyMapper;
import com.springboot.cloud.dto.*;
import com.springboot.cloud.dto.EsProduct;
import com.springboot.cloud.entity.*;
import com.springboot.cloud.service.*;

import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 商品管理Service实现类
 * Created by macro on 2018/4/26.
 */
@Service
public class MallGoodsInfoServiceImpl implements MallGoodsInfoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MallGoodsInfoServiceImpl.class);
    @Autowired
    MallGoodsInfoMapper mallGoodsInfoMapper;

    @Autowired
    MallCategoryParamService mallCategoryParamService;

    @Autowired
    MallGoodsParamService mallGoodsParamService;

    @Autowired
    MallGoodsPropertyMapper mallGoodsPropertyMapper;

    @Autowired
    MallGoodsGrandService mallGoodsGrandService;

    @Autowired
    MallGoodsPropertyRelateService mallGoodsPropertyRelateService;

    @Autowired
    MallGrandService mallGrandService;

    @Autowired
    MallGoodsCategoryService mallGoodsCategoryService;

    @Autowired
    MallSearchService mallSearchService;




    @Override
    @Transactional(rollbackForClassName = "Exception.class")
    public int create(ProductAddParam productAddParam) {
        int count;
        //创建商品
//        MallGoodsInfo mallGoodsInfo = mallGoods;
        MallGoodsInfo mallGoodsInfo =(MallGoodsInfo) productAddParam;
        //注意要调整库存的数量以及已售卖数量
        Integer sellingStock = productAddParam.getProductProperties().stream().collect(Collectors.summingInt(ProductProperty::getSelledStockNumPro));
        Integer stock = productAddParam.getProductProperties().stream().collect(Collectors.summingInt(ProductProperty::getStockNumPro));
        Comparator<Double> comparator = Comparator.comparing(Double::doubleValue);
        Double originPrice = productAddParam.getProductProperties().stream().map(ProductProperty::getOriginalPricePro).min(comparator).get();
        Double sellingPrice=productAddParam.getProductProperties().stream().map(ProductProperty::getSellingPricePro).min(comparator).get();
        mallGoodsInfo.setSelledStockNum(sellingStock);
        mallGoodsInfo.setStockNum(stock);
        mallGoodsInfo.setOriginalPrice(new BigDecimal(originPrice));
        mallGoodsInfo.setSellingPrice(new BigDecimal(sellingPrice));
        //插入商品信息
        mallGoodsInfoMapper.insert(mallGoodsInfo);
        productAddParam.setGoodsId(mallGoodsInfo.getGoodsId());
        //添加商品与品牌的关联
        MallGoodsGrand mallGoodsGrand = generateGrand(productAddParam);
        mallGoodsGrandService.add(mallGoodsGrand);
        //添加商品与属性关联关系
        List<MallGoodsPropertyRelate> mallGoodsPropertyRelates = generatePropertyRelate(productAddParam);
        mallGoodsPropertyRelateService.addList(mallGoodsPropertyRelates);
        //添加商品与库存之间的关系
        List<MallGoodsProperty> mallGoodsProperties = generateGoodProperty(productAddParam);
        mallGoodsPropertyMapper.insertAll(mallGoodsProperties);
        //添加商品与参数之间的关系
        List<MallGoodsParam> mallGoodsParams = generateGoodParam(productAddParam);
        mallGoodsParamService.createList(mallGoodsParams);

        //调用elasticSearch服务,插入新添加的商品
        EsProduct esProduct = new EsProduct();
        changGoodInfoToEsProduct(esProduct,mallGoodsInfo);
        mallSearchService.create(esProduct);



        //查询出某个分类所涉及的参数
//        Iterator<String> paramKey= mallCategoryParamService.selectByCategoryId(mallGoodsInfo.getGoodsCategoryId()).stream().map(MallCategoryParam::getCategoryParamName)
//                .collect(Collectors.toList()).iterator();
//        //添加值为空的关于商品分类所设置参数
//        List<MallGoodsParam> mallGoodsParams = new ArrayList<>();
//        while(paramKey.hasNext()){
//            MallGoodsParam mallGoodsParam = new MallGoodsParam();
//            mallGoodsParam.setCreateUser(mallGoodsInfo.getCreateUser());
//            mallGoodsParam.setUpdateUser(mallGoodsInfo.getUpdateUser());
//            mallGoodsParam.setGoodsId(mallGoodsInfo.getGoodsId());
//            mallGoodsParam.setGoodsParamName(paramKey.next());
//            mallGoodsParam.setGoodsParamValue("无");
//            mallGoodsParams.add(mallGoodsParam);
//        }
//        mallGoodsParamService.createList(mallGoodsParams);

//        //根据促销类型设置价格：会员价格、阶梯价格、满减价格
//        Long goodsId = mallGoodsInfo.getGoodsId();
//        //会员价格
//        relateAndInsertList(memberPriceDao, ProductAddParam.getMemberPriceList(), productId);
//        //阶梯价格
//        relateAndInsertList(productLadderDao, ProductAddParam.getProductLadderList(), productId);
//        //满减价格
//        relateAndInsertList(productFullReductionDao, ProductAddParam.getProductFullReductionList(), productId);
//        //处理sku的编码
//        handleSkuStockCode(ProductAddParam.getSkuStockList(),productId);
//        //添加sku库存信息
//        relateAndInsertList(skuStockDao, ProductAddParam.getSkuStockList(), productId);
//        //添加商品参数,添加自定义商品规格
//        relateAndInsertList(productAttributeValueDao, ProductAddParam.getProductAttributeValueList(), productId);
//        //关联专题
//        relateAndInsertList(subjectProductRelationDao, ProductAddParam.getSubjectProductRelationList(), productId);
//        //关联优选
//        relateAndInsertList(prefrenceAreaProductRelationDao, ProductAddParam.getPrefrenceAreaProductRelationList(), productId);
        count = 1;
        return count;
    }

    class myBigDecimalComparator implements Comparator<BigDecimal> {
        @Override
        public int compare(BigDecimal a, BigDecimal b) {
            if(a.compareTo(b)>=0){
                return 1;
            }else{
                return 0;
            }

        }
    }

    MallGoodsGrand  generateGrand(ProductAddParam productAddParam){
        MallGoodsGrand mallGoodsGrand = new MallGoodsGrand();
        mallGoodsGrand.setGoodsId(productAddParam.getGoodsId());
        mallGoodsGrand.setGrandId(productAddParam.getGrandId());
        mallGoodsGrand.setCreateUser(productAddParam.getCreateUser());
        mallGoodsGrand.setUpdateUser(productAddParam.getUpdateUser());
        return  mallGoodsGrand;
    }

    List<MallGoodsPropertyRelate>  generatePropertyRelate(ProductAddParam productAddParam){
        List<MallGoodsPropertyRelate> mallGoodsPropertyRelates = new ArrayList<>();
        Iterator<ProductPropertyRelate> iterator = productAddParam.getProductPropertyRelates().iterator();
        while(iterator.hasNext()){
            MallGoodsPropertyRelate mallGoodsPropertyRelate = new MallGoodsPropertyRelate();
            ProductPropertyRelate productPropertyRelate  = iterator.next();
            mallGoodsPropertyRelate.setGoodsId(productAddParam.getGoodsId());
            mallGoodsPropertyRelate.setCategoryPropertyId(productPropertyRelate.getCategoryPropertyId());
            mallGoodsPropertyRelate.setGoodsPropertyRelateName(productPropertyRelate.getGoodsPropertyRelateName());
            mallGoodsPropertyRelate.setGoodsPropertyRelateValue(productPropertyRelate.getGoodsPropertyRelateValue());
            mallGoodsPropertyRelates.add(mallGoodsPropertyRelate);
        }

        return  mallGoodsPropertyRelates;
    }

    List<MallGoodsProperty>  generateGoodProperty(ProductAddParam productAddParam){
        List<MallGoodsProperty> mallGoodsProperties = new ArrayList<>();
        Iterator<ProductProperty> iterator = productAddParam.getProductProperties().iterator();
        while(iterator.hasNext()){
            MallGoodsProperty mallGoodsProperty = new MallGoodsProperty();
            ProductProperty productProperty  = iterator.next();
            mallGoodsProperty.setCreateUser(productAddParam.getCreateUser());
            mallGoodsProperty.setUpdateUser(productAddParam.getUpdateUser());
            mallGoodsProperty.setGoodsId(productAddParam.getGoodsId());
            mallGoodsProperty.setGoodsPropertyValue(productProperty.getGoodsPropertyValue());
            mallGoodsProperty.setOriginalPrice(BigDecimal.valueOf(productProperty.getOriginalPricePro()));
            mallGoodsProperty.setSellingPrice(BigDecimal.valueOf(productProperty.getSellingPricePro()));
            mallGoodsProperty.setStockNum(productProperty.getStockNumPro());
            mallGoodsProperty.setSelledStockNum(productProperty.getSelledStockNumPro());
            mallGoodsProperty.setGoodsPropertyName("");
            mallGoodsProperties.add(mallGoodsProperty);
        }
        return  mallGoodsProperties;
    }

    List<MallGoodsParam>  generateGoodParam(ProductAddParam productAddParam){
        List<MallGoodsParam> mallGoodsParams = new ArrayList<>();
        Iterator<ProductParam> iterator = productAddParam.getProductParams().iterator();
        while(iterator.hasNext()){
            MallGoodsParam mallGoodsParam = new MallGoodsParam();
            ProductParam productParam  = iterator.next();
            mallGoodsParam.setGoodsId(productAddParam.getGoodsId());
            mallGoodsParam.setGoodsParamName(productParam.getGoodsParamName());
            mallGoodsParam.setGoodsParamValue(productParam.getGoodsParamValue());
            mallGoodsParam.setUpdateUser(productAddParam.getUpdateUser());
            mallGoodsParam.setCreateUser(productAddParam.getCreateUser());
            mallGoodsParams.add(mallGoodsParam);
        }

        return  mallGoodsParams;
    }



    @Override
    public int update(Long id, MallGoodsInfo mallGoodsInfo){
        mallGoodsInfo.setGoodsId(id);
        return mallGoodsInfoMapper.updateByPrimaryKey(mallGoodsInfo);
    }

    @Override
    public List<MallGoodsInfo> list(MallGoodsInfoQueryParam productQueryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return mallGoodsInfoMapper.selectPageByManyCondition(productQueryParam);
    }


    @Override
    public  MallGoodsInfo selectItem(Long id){
        return mallGoodsInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<EsProduct> getAllEsProduct() {
        //查询所有的商品  对应的品牌id和name  分类id和name  商品属性
        List<MallGoodsInfo>  goodsInfos = mallGoodsInfoMapper.selectAll();
        List<EsProduct> esProducts = new ArrayList<>();
        goodsInfos.forEach(item ->{
            EsProduct esProduct = new EsProduct();
            changGoodInfoToEsProduct(esProduct,item);
            esProducts.add(esProduct);
        });

        return esProducts;
    }

    void  changGoodInfoToEsProduct(EsProduct esProduct,MallGoodsInfo goodsInfo){
        esProduct.setGoodsId(goodsInfo.getGoodsId());
        esProduct.setGoodsName(goodsInfo.getGoodsName());
        esProduct.setGoodsCoverImg(goodsInfo.getGoodsCoverImg());
        esProduct.setSellingPrice(goodsInfo.getSellingPrice());
        esProduct.setSelledStockNum(goodsInfo.getSelledStockNum());
        esProduct.setStockNum(goodsInfo.getStockNum());
        esProduct.setGoodsSellStatus(goodsInfo.getGoodsSellStatus());

        //根据分类id获取分类的名字
        MallGoodsCategory mallGoodsCategory = mallGoodsCategoryService.getItem(goodsInfo.getGoodsCategoryId());
        esProduct.setGoodsCategoryId(goodsInfo.getGoodsCategoryId());
        esProduct.setCategoryName(mallGoodsCategory.getCategoryName());

        //通过商品id获取所有商品品牌
        MallGoodsGrand goodsGrand = mallGoodsGrandService.selectGrandByGoodId(goodsInfo.getGoodsId());
        esProduct.setGrandId(goodsGrand.getGrandId());

        //通过品牌id获取品牌名
        MallGrand grand = mallGrandService.getBrand(goodsGrand.getGrandId());
        esProduct.setGrandName(grand.getGrandName());
        //通过商品id获取所有商品的参数
        List<MallGoodsParam> goodsParams = mallGoodsParamService.selectGoodsParamByGoodId(goodsInfo.getGoodsId());
        chageGoodParamsToEsProduct(esProduct,goodsParams);

    }

    void chageGoodParamsToEsProduct(EsProduct esProduct,List<MallGoodsParam> mallGoodsParam){

        List<EsProductParamValue> paramValues =  new ArrayList<>();
        mallGoodsParam.stream().forEach(item->{
            EsProductParamValue  paramValue= new EsProductParamValue();
            paramValue.setGoodsParamId(item.getGoodsParamId());
            paramValue.setGoodsParamName(item.getGoodsParamName());
            paramValue.setGoodsParamValue(item.getGoodsParamValue());
            paramValues.add(paramValue);
        });
        esProduct.setAttrValueList(paramValues);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateProductStockByProperty(List<Long> goodIds) {
        goodIds.stream().forEach(item->{
            //根据orderId查询出所有的商品属性
            List<MallGoodsProperty> mallGoodsProperties = mallGoodsPropertyMapper.selectByGoodsId(item);
            Integer stock = mallGoodsProperties.stream().collect(Collectors.summingInt(MallGoodsProperty::getStockNum));
            Integer selledStock = mallGoodsProperties.stream().collect(Collectors.summingInt(MallGoodsProperty::getSelledStockNum));
            //对orderId进行更新
            mallGoodsInfoMapper.updateStockById(item,stock,selledStock);
        });
        return 1;
    }

    //    private void handleSkuStockCode(List<PmsSkuStock> skuStockList, Long productId) {
//        if(CollectionUtils.isEmpty(skuStockList))return;
//        for(int i=0;i<skuStockList.size();i++){
//            PmsSkuStock skuStock = skuStockList.get(i);
//            if(StringUtils.isEmpty(skuStock.getSkuCode())){
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//                StringBuilder sb = new StringBuilder();
//                //日期
//                sb.append(sdf.format(new Date()));
//                //四位商品id
//                sb.append(String.format("%04d", productId));
//                //三位索引id
//                sb.append(String.format("%03d", i+1));
//                skuStock.setSkuCode(sb.toString());
//            }
//        }
//    }

//    @Override
//    public PmsProductResult getUpdateInfo(Long id) {
//        return productDao.getUpdateInfo(id);
//    }

//    @Override
//    public int update(Long id, PmsProductParam ProductAddParam) {
//        int count;
//        //更新商品信息
//        PmsProduct product = ProductAddParam;
//        product.setId(id);
//        productMapper.updateByPrimaryKeySelective(product);
//        //会员价格
//        PmsMemberPriceExample pmsMemberPriceExample = new PmsMemberPriceExample();
//        pmsMemberPriceExample.createCriteria().andProductIdEqualTo(id);
//        memberPriceMapper.deleteByExample(pmsMemberPriceExample);
//        relateAndInsertList(memberPriceDao, ProductAddParam.getMemberPriceList(), id);
//        //阶梯价格
//        PmsProductLadderExample ladderExample = new PmsProductLadderExample();
//        ladderExample.createCriteria().andProductIdEqualTo(id);
//        productLadderMapper.deleteByExample(ladderExample);
//        relateAndInsertList(productLadderDao, ProductAddParam.getProductLadderList(), id);
//        //满减价格
//        PmsProductFullReductionExample fullReductionExample = new PmsProductFullReductionExample();
//        fullReductionExample.createCriteria().andProductIdEqualTo(id);
//        productFullReductionMapper.deleteByExample(fullReductionExample);
//        relateAndInsertList(productFullReductionDao, ProductAddParam.getProductFullReductionList(), id);
//        //修改sku库存信息
//        handleUpdateSkuStockList(id, ProductAddParam);
//        //修改商品参数,添加自定义商品规格
//        PmsProductAttributeValueExample productAttributeValueExample = new PmsProductAttributeValueExample();
//        productAttributeValueExample.createCriteria().andProductIdEqualTo(id);
//        productAttributeValueMapper.deleteByExample(productAttributeValueExample);
//        relateAndInsertList(productAttributeValueDao, ProductAddParam.getProductAttributeValueList(), id);
//        //关联专题
//        CmsSubjectProductRelationExample subjectProductRelationExample = new CmsSubjectProductRelationExample();
//        subjectProductRelationExample.createCriteria().andProductIdEqualTo(id);
//        subjectProductRelationMapper.deleteByExample(subjectProductRelationExample);
//        relateAndInsertList(subjectProductRelationDao, ProductAddParam.getSubjectProductRelationList(), id);
//        //关联优选
//        CmsPrefrenceAreaProductRelationExample prefrenceAreaExample = new CmsPrefrenceAreaProductRelationExample();
//        prefrenceAreaExample.createCriteria().andProductIdEqualTo(id);
//        prefrenceAreaProductRelationMapper.deleteByExample(prefrenceAreaExample);
//        relateAndInsertList(prefrenceAreaProductRelationDao, ProductAddParam.getPrefrenceAreaProductRelationList(), id);
//        count = 1;
//        return count;
//    }

//    private void handleUpdateSkuStockList(Long id, PmsProductParam ProductAddParam) {
//        //当前的sku信息
//        List<PmsSkuStock> currSkuList = ProductAddParam.getSkuStockList();
//        //当前没有sku直接删除
//        if(CollUtil.isEmpty(currSkuList)){
//            PmsSkuStockExample skuStockExample = new PmsSkuStockExample();
//            skuStockExample.createCriteria().andProductIdEqualTo(id);
//            skuStockMapper.deleteByExample(skuStockExample);
//            return;
//        }
//        //获取初始sku信息
//        PmsSkuStockExample skuStockExample = new PmsSkuStockExample();
//        skuStockExample.createCriteria().andProductIdEqualTo(id);
//        List<PmsSkuStock> oriStuList = skuStockMapper.selectByExample(skuStockExample);
//        //获取新增sku信息
//        List<PmsSkuStock> insertSkuList = currSkuList.stream().filter(item->item.getId()==null).collect(Collectors.toList());
//        //获取需要更新的sku信息
//        List<PmsSkuStock> updateSkuList = currSkuList.stream().filter(item->item.getId()!=null).collect(Collectors.toList());
//        List<Long> updateSkuIds = updateSkuList.stream().map(PmsSkuStock::getId).collect(Collectors.toList());
//        //获取需要删除的sku信息
//        List<PmsSkuStock> removeSkuList = oriStuList.stream().filter(item-> !updateSkuIds.contains(item.getId())).collect(Collectors.toList());
//        handleSkuStockCode(insertSkuList,id);
//        handleSkuStockCode(updateSkuList,id);
//        //新增sku
//        if(CollUtil.isNotEmpty(insertSkuList)){
//            relateAndInsertList(skuStockDao, insertSkuList, id);
//        }
//        //删除sku
//        if(CollUtil.isNotEmpty(removeSkuList)){
//            List<Long> removeSkuIds = removeSkuList.stream().map(PmsSkuStock::getId).collect(Collectors.toList());
//            PmsSkuStockExample removeExample = new PmsSkuStockExample();
//            removeExample.createCriteria().andIdIn(removeSkuIds);
//            skuStockMapper.deleteByExample(removeExample);
//        }
//        //修改sku
//        if(CollUtil.isNotEmpty(updateSkuList)){
//            for (PmsSkuStock pmsSkuStock : updateSkuList) {
//                skuStockMapper.updateByPrimaryKeySelective(pmsSkuStock);
//            }
//        }
//
//    }



//    @Override
//    public int updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail) {
//        PmsProduct product = new PmsProduct();
//        product.setVerifyStatus(verifyStatus);
//        PmsProductExample example = new PmsProductExample();
//        example.createCriteria().andIdIn(ids);
//        List<PmsProductVertifyRecord> list = new ArrayList<>();
//        int count = productMapper.updateByExampleSelective(product, example);
//        //修改完审核状态后插入审核记录
//        for (Long id : ids) {
//            PmsProductVertifyRecord record = new PmsProductVertifyRecord();
//            record.setProductId(id);
//            record.setCreateTime(new Date());
//            record.setDetail(detail);
//            record.setStatus(verifyStatus);
//            record.setVertifyMan("test");
//            list.add(record);
//        }
//        productVertifyRecordDao.insertList(list);
//        return count;
//    }

    @Override
    public int updatePublishStatus(List<Long> ids, Integer publishStatus) {
        Iterator<Long> iterator = ids.iterator();
        Map<String,Object> maps = new HashMap<>();
        maps.put("list", ids);
        maps.put("Status", publishStatus);

        return mallGoodsInfoMapper.updateSellStatueByGoodsId(maps);
    }

    @Override
    public ProductAddParam getProductAddParamByGoodId(Long goodId) {
           //查找出GoodId

           //MallGoodsGrand

          // List<MallGoodsPropertyRelate>
          //List<MallGoodsProperty>
          // List<MallGoodsParam>
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateDeleteStatus(List<Long> ids) {
        Iterator<Long> iterator = ids.iterator();
        while(iterator.hasNext()){
            //删除品牌相关
            Long item = iterator.next();
            mallGoodsGrandService.deleteByGoodId(item);
            //删除属性
            mallGoodsPropertyMapper.deleteByGoodId(item);
            //删除属性相关
            mallGoodsPropertyRelateService.deleteByGoodId(item);
            //删除参数相关
            mallGoodsParamService.deleteByGoodId(item);
            //删除商品
            mallGoodsInfoMapper.deleteByPrimaryKey(item);
            mallSearchService.delete(item);
        }

        return 1;

    }

    //    @Override
//    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
//        PmsProduct record = new PmsProduct();
//        record.setRecommandStatus(recommendStatus);
//        PmsProductExample example = new PmsProductExample();
//        example.createCriteria().andIdIn(ids);
//        return productMapper.updateByExampleSelective(record, example);
//    }
//
//    @Override
//    public int updateNewStatus(List<Long> ids, Integer newStatus) {
//        PmsProduct record = new PmsProduct();
//        record.setNewStatus(newStatus);
//        PmsProductExample example = new PmsProductExample();
//        example.createCriteria().andIdIn(ids);
//        return productMapper.updateByExampleSelective(record, example);
//    }

//    @Override
//    public int updateDeleteStatus(List<Long> ids, Integer deleteStatus) {
//        PmsProduct record = new PmsProduct();
//        record.setDeleteStatus(deleteStatus);
//        PmsProductExample example = new PmsProductExample();
//        example.createCriteria().andIdIn(ids);
//        return productMapper.updateByExampleSelective(record, example);
//    }

//    @Override
//    public List<PmsProduct> list(String keyword) {
//        PmsProductExample productExample = new PmsProductExample();
//        PmsProductExample.Criteria criteria = productExample.createCriteria();
//        criteria.andDeleteStatusEqualTo(0);
//        if(!StringUtils.isEmpty(keyword)){
//            criteria.andNameLike("%" + keyword + "%");
//            productExample.or().andDeleteStatusEqualTo(0).andProductSnLike("%" + keyword + "%");
//        }
//        return productMapper.selectByExample(productExample);
//    }

    /**
     * 建立和插入关系表操作
     *
     * @param dao       可以操作的dao
     * @param dataList  要插入的数据
     * @param productId 建立关系的id
     */
//    private void relateAndInsertList(Object dao, List dataList, Long productId) {
//        try {
//            if (CollectionUtils.isEmpty(dataList)) return;
//            for (Object item : dataList) {
//                Method setId = item.getClass().getMethod("setId", Long.class);
//                setId.invoke(item, (Long) null);
//                Method setProductId = item.getClass().getMethod("setProductId", Long.class);
//                setProductId.invoke(item, productId);
//            }
//            Method insertList = dao.getClass().getMethod("insertList", List.class);
//            insertList.invoke(dao, dataList);
//        } catch (Exception e) {
//            LOGGER.warn("创建产品出错:{}", e.getMessage());
//            throw new RuntimeException(e.getMessage());
//        }
//    }


    @Override
    public List<MallGoodsInfo>  searchTimeInMysql(EsProductQuery esProductQuery) {
        PageHelper.startPage(esProductQuery.getPageNum(),  esProductQuery.getPageSize());
        List<MallGoodsInfo> mallGoods = mallGoodsInfoMapper.searchTime(esProductQuery);
        return mallGoods;
    }
}
