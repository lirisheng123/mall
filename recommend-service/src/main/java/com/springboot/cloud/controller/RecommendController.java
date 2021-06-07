package com.springboot.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.springboot.cloud.entity.MallGoodsInfo;
import com.springboot.cloud.service.RecommendService;
import com.springboot.cloud.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/5/5 18:14
 * @Version 1.0
 */
@Controller
@Api(tags = "RecommendController", description = "推荐商品")
@ResponseBody
@RequestMapping("/recommend")
@Slf4j
public class RecommendController {

    @Autowired
    RecommendService recommendService;


    @ApiOperation("根据用户Id获取推荐商品")
    @RequestMapping(value = "/getRecommendGoodInfo/{userId}", method = RequestMethod.GET)
    public CommonResult<List<MallGoodsInfo>> getRecommend(@PathVariable("userId")Long userId, @RequestParam("size")Integer size,
                                                    @RequestParam("recommendType")String recommendType){


        List<MallGoodsInfo> mallGoodsInfos =null;
        if(recommendType.equals("userBased")){
            //基于用户
            log.debug("enter userBased");
            mallGoodsInfos = recommendService.userBasedRecommender(userId,size);
        }else if(recommendType.equals("itemBased")){
            //基于内容
            mallGoodsInfos = recommendService.itemBasedRecommender(userId,size);
        }else if(recommendType.equals("slopeOne")){
            //slope one
            mallGoodsInfos = recommendService.slopeOneRecommender(userId,size);
        }
        return CommonResult.success(mallGoodsInfos);
    }
}
