package com.springboot.cloud.service.impl;

import com.github.pagehelper.PageHelper;
import com.springboot.cloud.dao.MallFlashPromotionSessionMapper;
import com.springboot.cloud.dto.FlashPromotionParams;
import com.springboot.cloud.dto.SecondParams;
import com.springboot.cloud.entity.MallFlashPromotionSession;
import com.springboot.cloud.entity.MallSecondProperty;
import com.springboot.cloud.service.MallFlashPromotionSessionService;
import com.springboot.cloud.service.MallSecondPropertyService;
import com.springboot.cloud.service.SecondService;
import com.springboot.cloud.util.CommonResult;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: lirisheng
 * @Date: 2021/3/30 0:57
 * @Version 1.0
 */
@Service
@Slf4j
public class MallFlashPromotionSessionServiceImpl  implements MallFlashPromotionSessionService {
    @Autowired
    MallFlashPromotionSessionMapper mallFlashPromotionSessionMapper;

    @Autowired
    MallSecondPropertyService mallSecondPropertyService;

    @Autowired
    SecondService secondService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(SecondParams secondParams) {
        MallFlashPromotionSession flashPromotionSession = secondParams;
        //
        Integer count=secondParams.getMallSecondProperties().stream().mapToInt(MallSecondProperty::getPropertyCount).sum();
        Comparator<BigDecimal> comparator = new myBigDecimalComparator();
        BigDecimal price = secondParams.getMallSecondProperties().stream().map(MallSecondProperty::getPropertyPrice).min(comparator).get();
        BigDecimal sellingPrice=secondParams.getMallSecondProperties().stream().map(MallSecondProperty::getPropertySellingPrice).min(comparator).get();
        log.debug("count:"+count);
        log.debug("price:"+price);
        log.debug("sellingPrice:"+sellingPrice);
        flashPromotionSession.setCount(count);
        flashPromotionSession.setPrice(price);
        flashPromotionSession.setSellingPrice(sellingPrice);
        mallFlashPromotionSessionMapper.insert(flashPromotionSession);
        //????????????????????????
        secondParams.getMallSecondProperties().stream().forEach(item->{
            item.setMallFlashPromotionId(flashPromotionSession.getMallFlashPromotionId());
            mallSecondPropertyService.insert(item);
            //??????
            if(secondService.initRedisStock(item).getCode()!=200){
                //???????????????
                log.info("???????????????  ????????????");
            }

        });
        return 1;
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteList(List<Long> ids) {
        //????????????????????????????????????,???????????????????????????
//        ids.stream().filter(item->{
//            return item==0;
//        }).collect(Collectors.toList());

        return mallFlashPromotionSessionMapper.deleteList(ids);
    }

    @Override
    public int update(Long id, MallFlashPromotionSession mallFlashPromotionSession) {
        //status????????????,??????0???????????????????????????
        if(mallFlashPromotionSession.getStatus()==0){
             mallFlashPromotionSession.setMallFlashPromotionId(id);
            return  mallFlashPromotionSessionMapper.updateByPrimaryKey(mallFlashPromotionSession);
        }else{
            return 0;
        }

    }

    @Override
    public List<MallFlashPromotionSession> selectList(FlashPromotionParams flashPromotionParams, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        //??????
        mallFlashPromotionSessionMapper.selectList(flashPromotionParams).stream().forEach(item->{
             if(item.getStatus()==1){
                 //??????????????????????????????????????????
                 List<MallSecondProperty> mallSecondProperties = mallSecondPropertyService.selectByFlashId(item.getMallFlashPromotionId());

                 mallSecondProperties.stream().forEach(item1->{
//                     log.debug("mallsecondProerty:"+item1);
                     CommonResult commonResult = secondService.updateFlashInfo(item1);
                     if(commonResult.getCode()==200){
                         item1=(MallSecondProperty) commonResult.getData();
//                         log.debug("mallsecondProerty1:"+item1);
                         if(mallSecondPropertyService.update(item1.getMallSecondPropertyId(),item1)==0){
                             log.debug("???????????????  ????????????????????????  ");
                         }

                     }else{
                         log.debug("??????????????? ???redis???????????????????????????  ");
                     }
                 });
                 Integer count = mallSecondProperties.stream().mapToInt(MallSecondProperty::getPropertyCount).sum();
                 Integer saledCount= mallSecondProperties.stream().mapToInt(MallSecondProperty::getPropertySelledCount).sum();
                 item.setCount(count);
                 item.setSelledCount(saledCount);
                 if(mallFlashPromotionSessionMapper.updateByPrimaryKey(item)==0){
                     log.debug("??????????????? ?????????????????????  ");
                 }
             }
        });
        return  mallFlashPromotionSessionMapper.selectList(flashPromotionParams);
    }

//    @Override
//    public int updateStatus(Long id, Integer status) {
//        return mallFlashPromotionSessionMapper.updateStatus(id,status);
//    }

    @Override
    public MallFlashPromotionSession select(Long id) {
        return mallFlashPromotionSessionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateStatusById(Long id,Integer status) {

        if(status==0){
            //?????????
           List<Long> ids= mallSecondPropertyService.selectByFlashId(id).stream().map(MallSecondProperty::getGoodsPropertyId).collect(Collectors.toList());
            if(secondService.deleteRedisStock(ids).getCode()!=200){
                log.debug("status???????????? ,redis??????????????????");
            }
            return  mallFlashPromotionSessionMapper.updateStatus(id,status);
        }else if(status==1){
            //??????
            mallSecondPropertyService.selectByFlashId(id).stream().forEach(item->{
                //??????
                if(secondService.initRedisStock(item).getCode()!=200){
                    //???????????????
                    log.info("?????? status  ????????????");
                }

            });
            return  mallFlashPromotionSessionMapper.updateStatus(id,status);
        }else{
            return 0;
        }



    }
}
