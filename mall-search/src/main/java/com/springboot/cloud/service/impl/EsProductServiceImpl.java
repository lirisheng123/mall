package com.springboot.cloud.service.impl;



import com.springboot.cloud.domain.EsProduct;
import com.springboot.cloud.domain.EsProductRelatedInfo;
import com.springboot.cloud.dto.EsProductQuery;
import com.springboot.cloud.repository.EsProductRepository;
import com.springboot.cloud.service.EsProductService;
import com.springboot.cloud.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.ParsedFilter;
import org.elasticsearch.search.aggregations.bucket.nested.ParsedNested;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 搜索商品管理Service实现类
 * Created by macro on 2018/6/19.
 */
@Service
@Slf4j
public class EsProductServiceImpl implements EsProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EsProductServiceImpl.class);

    @Autowired
    private EsProductRepository productRepository;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private ProductService productService;


    @Override
    public int importAll() {
        List<EsProduct> esProductList =productService.getAllEsProduct().getData();
        Iterable<EsProduct> esProductIterable = productRepository.saveAll(esProductList);
        Iterator<EsProduct> iterator = esProductIterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public EsProduct create(EsProduct esProduct) {
        EsProduct result = null;
//        List<EsProduct> esProductList = productDao.getAllEsProductList(id);
//        if (esProductList.size() > 0) {
//            EsProduct esProduct = esProductList.get(0);
//            result = productRepository.save(esProduct);
//        }
        result = productRepository.save(esProduct);
        return result;
    }

    @Override
    public void delete(List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<EsProduct> esProductList = new ArrayList<>();
            for (Long id : ids) {
                EsProduct esProduct = new EsProduct();
                esProduct.setGoodsId(id);
                esProductList.add(esProduct);
            }
            productRepository.deleteAll(esProductList);
        }
    }

//    @Override
//    public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
//        Pageable pageable = PageRequest.of(pageNum, pageSize);
//        return productRepository.findByNameOrSubTitleOrKeywords(keyword, keyword, keyword, pageable);
//    }

//    @Override
//    public Page<EsProduct> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize,Integer sort) {
//        Pageable pageable = PageRequest.of(pageNum, pageSize);
//        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
//        //分页
//        nativeSearchQueryBuilder.withPageable(pageable);
//        //过滤
//        //注意要默认过滤掉下架的商品
//        if (brandId != null || productCategoryId != null) {
//            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//            if (brandId != null) {
//                //匹配多个值
//                boolQueryBuilder.must(QueryBuilders.termQuery("brandId", brandId));
//
//            }
//            if (productCategoryId != null) {
//                //匹配多个值
//                boolQueryBuilder.must(QueryBuilders.termQuery("productCategoryId", productCategoryId));
//            }
//
//
//            nativeSearchQueryBuilder.withFilter(boolQueryBuilder);
//        }
//        //搜索
//        if (StringUtils.isEmpty(keyword)) {
//            nativeSearchQueryBuilder.withQuery(QueryBuilders.matchAllQuery());
//        } else {
//            List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
//            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("name", keyword),
//                    ScoreFunctionBuilders.weightFactorFunction(10)));
//            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("subTitle", keyword),
//                    ScoreFunctionBuilders.weightFactorFunction(5)));
//            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("keywords", keyword),
//                    ScoreFunctionBuilders.weightFactorFunction(2)));
//            FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
//            filterFunctionBuilders.toArray(builders);
//            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
//                    .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
//                    .setMinScore(2);
//            nativeSearchQueryBuilder.withQuery(functionScoreQueryBuilder);
//        }
//        //排序
//        if(sort==1){
//            //按新品从新到旧
//            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC));
//        }else if(sort==2){
//            //按销量从高到低
//            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("sale").order(SortOrder.DESC));
//        }else if(sort==3){
//            //按价格从低到高
//            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));
//        }else if(sort==4){
//            //按价格从高到低
//            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
//        }else{
//            //按相关度
//            nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
//        }
//        nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
//        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();
//        LOGGER.info("DSL:{}", searchQuery.getQuery().toString());
//        SearchHits<EsProduct> searchHits = elasticsearchRestTemplate.search(searchQuery, EsProduct.class);
//        if(searchHits.getTotalHits()<=0){
//            return new PageImpl<>(null,pageable,0);
//        }
//        List<EsProduct> searchProductList = searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
//        return new PageImpl<>(searchProductList,pageable,searchHits.getTotalHits());
//    }

    @Override
    public Page<EsProduct> search(EsProductQuery esProductQuery) {

        Pageable pageable = PageRequest.of(esProductQuery.getPageNum(), esProductQuery.getPageSize());
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //分页
        nativeSearchQueryBuilder.withPageable(pageable);
        //过滤
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //注意要默认过滤掉下架的商品
        boolQueryBuilder.must(QueryBuilders.termQuery("goodsSellStatus", 0));
        if (esProductQuery.getBrandId() != null || esProductQuery.getProductCategoryId() != null||esProductQuery.getProductParams()!=null) {
//            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if (esProductQuery.getBrandId() != null) {
                //匹配多个值
                boolQueryBuilder.must(QueryBuilders.termsQuery("grandId", esProductQuery.getBrandId()));

            }
            if (esProductQuery.getProductCategoryId() != null) {
                //匹配多个值
                boolQueryBuilder.must(QueryBuilders.termsQuery("goodsCategoryId", esProductQuery.getProductCategoryId()));
            }
            if (esProductQuery.getProductParams() != null) {
                esProductQuery.getProductParams().stream().forEach(item-> {
                    log.debug("enter the getProductParams  elasticsearch");
                    log.debug("valueL"+item.getGoodsParamValue());
                    BoolQueryBuilder nestedQueryItem = QueryBuilders.boolQuery();
                    nestedQueryItem.must(QueryBuilders.termQuery("attrValueList.goodsParamName",item.getGoodsParamName()));
                    nestedQueryItem.must(QueryBuilders.termsQuery("attrValueList.goodsParamValue",item.getGoodsParamValue()));
                    NestedQueryBuilder nestedQuery=  QueryBuilders.nestedQuery("attrValueList",nestedQueryItem, ScoreMode.Avg);
                    boolQueryBuilder.must(nestedQuery);
                });

            }
            nativeSearchQueryBuilder.withFilter(boolQueryBuilder);
        }
        //搜索
        if (StringUtils.isEmpty(esProductQuery.getKeyword())) {
            nativeSearchQueryBuilder.withQuery(QueryBuilders.matchAllQuery());
        } else {
            nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("goodsName",esProductQuery.getKeyword()));
        }
        //排序
        if(esProductQuery.getSort()==1){
            //按新品从新到旧
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("goodsId").order(SortOrder.DESC));
        }else if(esProductQuery.getSort()==2){
            //按销量从高到低
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("selledStockNum").order(SortOrder.DESC));
        }else if(esProductQuery.getSort()==3){
            //按价格从低到高
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("sellingPrice").order(SortOrder.ASC));
        }else if(esProductQuery.getSort()==4){
            //按价格从高到低
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("sellingPrice").order(SortOrder.DESC));
        }else{
            //按相关度
            nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        }
        nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();
        LOGGER.info("DSL:{}", searchQuery.getQuery().toString());
        SearchHits<EsProduct> searchHits = elasticsearchRestTemplate.search(searchQuery, EsProduct.class);
        if(searchHits.getTotalHits()<=0){
            return new PageImpl<>(new ArrayList<EsProduct>(),pageable,0);
        }
        List<EsProduct> searchProductList = searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
        return new PageImpl<>(searchProductList,pageable,searchHits.getTotalHits());
    }

//    @Override
//    public Page<EsProduct> recommend(Long id, Integer pageNum, Integer pageSize) {
//        Pageable pageable = PageRequest.of(pageNum, pageSize);
//        List<EsProduct> esProductList = productDao.getAllEsProductList(id);
//        if (esProductList.size() > 0) {
//            EsProduct esProduct = esProductList.get(0);
//            String keyword = esProduct.getGoodsName();
//            Long brandId = esProduct.getGrandId();
//            Long productCategoryId = esProduct.getGoodsCategoryId();
//            //根据商品标题、品牌、分类进行搜索
//            List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
//            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("name", keyword),
//                    ScoreFunctionBuilders.weightFactorFunction(8)));
//            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("subTitle", keyword),
//                    ScoreFunctionBuilders.weightFactorFunction(2)));
//            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("keywords", keyword),
//                    ScoreFunctionBuilders.weightFactorFunction(2)));
//            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("brandId", brandId),
//                    ScoreFunctionBuilders.weightFactorFunction(5)));
//            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("productCategoryId", productCategoryId),
//                    ScoreFunctionBuilders.weightFactorFunction(3)));
//            FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
//            filterFunctionBuilders.toArray(builders);
//            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
//                    .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
//                    .setMinScore(2);
//            //用于过滤掉相同的商品
//            BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
//            boolQueryBuilder.mustNot(QueryBuilders.termQuery("id",id));
//            //构建查询条件
//            NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
//            builder.withQuery(functionScoreQueryBuilder);
//            builder.withFilter(boolQueryBuilder);
//            builder.withPageable(pageable);
//            NativeSearchQuery searchQuery = builder.build();
//            LOGGER.info("DSL:{}", searchQuery.getQuery().toString());
//            SearchHits<EsProduct> searchHits = elasticsearchRestTemplate.search(searchQuery, EsProduct.class);
//            if(searchHits.getTotalHits()<=0){
//                return new PageImpl<>(null,pageable,0);
//            }
//            List<EsProduct> searchProductList = searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
//            return new PageImpl<>(searchProductList,pageable,searchHits.getTotalHits());
//        }
//        return new PageImpl<>(null);
//    }

    @Override
    public EsProductRelatedInfo searchRelatedInfo(String keyword) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //搜索条件
        if(StringUtils.isEmpty(keyword)){
            builder.withQuery(QueryBuilders.matchAllQuery());
        }else{
//            builder.withQuery(QueryBuilders.multiMatchQuery(keyword,"name","subTitle","keywords"));
            builder.withQuery(QueryBuilders.multiMatchQuery(keyword,"goodsName"));
        }
        //聚合搜索品牌名称

        builder.addAggregation(AggregationBuilders.terms("productGrands").field("grandId")
                  .subAggregation(AggregationBuilders.terms("grandNames")
                          .field("grandName")));
        //集合搜索分类名称
        builder.addAggregation(AggregationBuilders.terms("productCategorys").field("goodsCategoryId")
                .subAggregation(AggregationBuilders.terms("categoryNames")
                        .field("categoryName")));

        //聚合搜索商品属性，去除type=1的属性
        AbstractAggregationBuilder aggregationBuilder = AggregationBuilders.nested("productParams","attrValueList")
//                .subAggregation(AggregationBuilders.filter("productAttrs",QueryBuilders.termQuery("attrValueList.type",1))
//                        .subAggregation(AggregationBuilders.terms("attrIds")
//                                .field("attrValueList.goodsParamId")
                  .subAggregation(AggregationBuilders.terms("goodsParamNames")
                        .field("attrValueList.goodsParamName")
                          .subAggregation(AggregationBuilders.terms("goodsParamValues")
                                  .field("attrValueList.goodsParamValue")));
//                                  .subAggregation(AggregationBuilders.terms("attrNames")
//                                        .field("attrValueList.goodsParamName")));
        builder.addAggregation(aggregationBuilder);
        NativeSearchQuery searchQuery = builder.build();
        SearchHits<EsProduct> searchHits = elasticsearchRestTemplate.search(searchQuery, EsProduct.class);
        return convertProductRelatedInfo(searchHits);
    }

    /**
     * 将返回结果转换为对象
     */
    private EsProductRelatedInfo convertProductRelatedInfo(SearchHits<EsProduct> response) {
        EsProductRelatedInfo productRelatedInfo = new EsProductRelatedInfo();
        Map<String, Aggregation> aggregationMap = response.getAggregations().getAsMap();
        //设置品牌
        List<? extends Terms.Bucket> productGrands = ((ParsedLongTerms)aggregationMap.get("productGrands")).getBuckets();
        log.debug("aggregation grandName:"+productGrands);
        List<EsProductRelatedInfo.ProductGrand> productGrandList = new ArrayList<>();
        for (Terms.Bucket item : productGrands){

            EsProductRelatedInfo.ProductGrand productGrand = new EsProductRelatedInfo.ProductGrand();
            productGrand.setGrandId((Long)item.getKey());
            List<? extends Terms.Bucket> grandNames = ((ParsedStringTerms) item.getAggregations().get("grandNames")).getBuckets();
            if(!CollectionUtils.isEmpty(grandNames)){
                String grandName = grandNames.get(0).getKeyAsString();
                productGrand.setGrandName(grandName);
            }

            productGrandList.add(productGrand);
        }
        productRelatedInfo.setProductGrands(productGrandList);
        //设置分类
//        Aggregation productCategoryNames = aggregationMap.get("productCategorys");
        List<? extends Terms.Bucket> productCategorys =((ParsedLongTerms)aggregationMap.get("productCategorys")).getBuckets();
        log.debug("aggregation productCategorys:"+productCategorys);
        List<EsProductRelatedInfo.ProductCategory> productCategoryList = new ArrayList<>();
        for (Terms.Bucket item : productCategorys){

            EsProductRelatedInfo.ProductCategory productCategory = new EsProductRelatedInfo.ProductCategory();
            productCategory.setGoodsCategoryId((Long)item.getKey());
            List<? extends Terms.Bucket> categoryNames = ((ParsedStringTerms) item.getAggregations().get("categoryNames")).getBuckets();
            if(!CollectionUtils.isEmpty(categoryNames)){
                String  categoryName= categoryNames.get(0).getKeyAsString();
                productCategory.setCategoryName(categoryName);
            }

            productCategoryList.add(productCategory);
        }
        productRelatedInfo.setProductCategorys(productCategoryList);
//        productRelatedInfo.setProductGrands(productGrandList);
//        List< EsProductRelatedInfo.ProductAttr > productCategoryNameList = new ArrayList<>();
//        for(int i=0;i<((Terms) productCategoryNames).getBuckets().size();i++){
//            productCategoryNameList.add(((Terms) productCategoryNames).getBuckets().get(i).getKeyAsString());
//
//        }
//        productRelatedInfo.setProductCategoryNames(productCategoryNameList);
        //设置参数
        Aggregation productAttrs = aggregationMap.get("productParams");
        log.debug("aggregation allAttrValues:"+productAttrs);
        List<? extends Terms.Bucket> attrNames =((ParsedStringTerms)  ((ParsedNested) productAttrs).getAggregations().get("goodsParamNames")).getBuckets();
//        List<? extends Terms.Bucket> attrIds = ((ParsedLongTerms) ((ParsedFilter) ((ParsedNested) productAttrs).getAggregations().get("productAttrs")).getAggregations().get("attrIds")).getBuckets();
        List<EsProductRelatedInfo.ProductParam> attrList = new ArrayList<>();
        for (Terms.Bucket attrName : attrNames) {
            EsProductRelatedInfo.ProductParam attr = new EsProductRelatedInfo.ProductParam();
            attr.setGoodsParamName(attrName.getKeyAsString());
            List<String> attrValueList = new ArrayList<>();
            List<? extends Terms.Bucket> attrValues = ((ParsedStringTerms) attrName.getAggregations().get("goodsParamValues")).getBuckets();
//            List<? extends Terms.Bucket> attrNames = ((ParsedStringTerms) attrId.getAggregations().get("attrNames")).getBuckets();
            for (Terms.Bucket attrValue : attrValues) {
                attrValueList.add(attrValue.getKeyAsString());
            }
            attr.setGoodsParamValue(attrValueList);
//            if(!CollectionUtils.isEmpty(attrNames)){
//                String attrName = attrNames.get(0).getKeyAsString();
//                attr.setAttrName(attrName);
//            }
            attrList.add(attr);
        }
        productRelatedInfo.setProductParams(attrList);
        return productRelatedInfo;
    }
}
