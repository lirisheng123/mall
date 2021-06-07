package com.springboot.cloud.service.impl;

import com.springboot.cloud.entity.MallGoodsInfo;
import com.springboot.cloud.service.ProductService;
import com.springboot.cloud.service.RecommendService;


import com.springboot.cloud.util.MyDataModel;
import com.springboot.cloud.util.RecommendFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.jdbc.ConnectionPoolDataSource;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/5/5 18:22
 * @Version 1.0
 */
@Service
@Slf4j
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    DataSource dataSource;

    @Autowired
    ProductService productService;

    @Override
    public List<MallGoodsInfo> slopeOneRecommender(Long userId, Integer size) {

        return null;
    }

    @Override
    public List<MallGoodsInfo> userBasedRecommender(Long userId, Integer size) {
        List<RecommendedItem> recommendations = null;
        JDBCDataModel dataModel = null;
//        try {
//            dataModel= MyDataModel.myDataModel();
//            UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);//计算内容相似度
//            UserNeighborhood neighborhood = new NearestNUserNeighborhood(1, similarity, dataModel);//计算用户的“邻居”，这里将与该用户最近距离为 3 的用户设置为该用户的“邻居”。
//            Recommender recommender = new CachingRecommender(new GenericUserBasedRecommender(dataModel, neighborhood, similarity));//采用 CachingRecommender 为 RecommendationItem 进行缓存
//            recommendations = recommender.recommend(userId, 2);//得到推荐的结果，size是推荐结果的数目
////            Recommender recommender = new GenericItemBasedRecommender(dataModel, similarity);//构造推荐引擎
////            recommendations = recommender.recommend(userId, 1);//得到推荐结果
//            log.debug("success recommendations:"+recommendations);
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
        try {
            dataModel = MyDataModel.myDataModel();
            UserSimilarity userSimilarity = RecommendFactory.userSimilarity(RecommendFactory.SIMILARITY.EUCLIDEAN, dataModel);
            UserNeighborhood userNeighborhood = RecommendFactory.userNeighborhood(RecommendFactory.NEIGHBORHOOD.NEAREST, userSimilarity, dataModel, 2);
            RecommenderBuilder recommenderBuilder = RecommendFactory.userRecommender(userSimilarity, userNeighborhood, true);

            RecommendFactory.evaluate(RecommendFactory.EVALUATOR.AVERAGE_ABSOLUTE_DIFFERENCE, recommenderBuilder, null, dataModel, 0.8);
            RecommendFactory.statsEvaluator(recommenderBuilder, null, dataModel, 2);
            recommendations = recommenderBuilder.buildRecommender(dataModel).recommend(userId, size);
//            return  changeListToGoodInfo(list);
//        LongPrimitiveIterator iter = dataModel.getUserIDs();
//        while (iter.hasNext()) {
//            long uid = iter.nextLong();
//            System.out.println("recommended userId:"+uid);
//            List list = recommenderBuilder.buildRecommender(dataModel).recommend(uid,6);
//            RecommendFactory.showItems(uid, list, true);
//        }}
        }
        catch (Exception e){
            e.printStackTrace();
        }

        log.debug("recommendations:"+recommendations);
        return chageRecommendToGoodInfo(recommendations);
    }

    @Override
    public List<MallGoodsInfo> itemBasedRecommender(Long userId, Integer size) {

        List<RecommendedItem> recommendations = null;
        JDBCDataModel dataModel = null;
        try {// use JNDI
//            ConnectionPoolDataSource connectionPool=new ConnectionPoolDataSource(dataSource);
//            dataModel = new MySQLJDBCDataModel(connectionPool,"mall_recommend", "user_id", "good_id","preference",null);
            dataModel= MyDataModel.myDataModel();
            ItemSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);//计算内容相似度
            Recommender recommender = new GenericItemBasedRecommender(dataModel, similarity);//构造推荐引擎
            recommendations = recommender.recommend(userId, 1);//得到推荐结果
            log.debug("success recommendations:"+recommendations);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        log.debug("recommendations:"+recommendations);
        return chageRecommendToGoodInfo(recommendations);
    }

    public List<MallGoodsInfo> chageRecommendToGoodInfo(List<RecommendedItem> recommendedItems){

        if(recommendedItems==null){
            return null;
        }

        Iterator<Long>  iterator = recommendedItems.stream().map(RecommendedItem::getItemID).iterator();
        List<MallGoodsInfo> mallGoodsInfos = new ArrayList<>();
        while(iterator.hasNext()){
            MallGoodsInfo mallGoodsInfo = productService.getUpdateInfo(iterator.next()).getData();
            mallGoodsInfos.add(mallGoodsInfo);
        }
        return mallGoodsInfos;
    }


}
