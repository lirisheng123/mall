package com.springboot.cloud.service.impl;

import com.github.pagehelper.PageHelper;
import com.springboot.cloud.common.entity.MallGoodsProperty;
import com.springboot.cloud.common.entity.user.MallUser;
import com.springboot.cloud.dao.MallOrderReturnApplyMapper;
import com.springboot.cloud.dto.ReturnApplyParams;
import com.springboot.cloud.entity.MallOrder;
import com.springboot.cloud.entity.MallOrderReturnApply;
import com.springboot.cloud.service.*;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: lirisheng
 * @Date: 2021/2/23 11:46
 * @Version 1.0
 */
@Service
@Slf4j
public class MallOrderReturnApplyServiceImpl implements MallOrderReturnApplyService {

    @Autowired
    MallOrderReturnApplyMapper mallOrderReturnApplyMapper;

    @Autowired
    MallOrderService mallOrderService;

    @Autowired
    MallOrderItemService mallOrderItemService;

    @Autowired
    ProductService productService;

    @Override
    public int insert(MallOrderReturnApply mallOrderReturnApply) {
        return mallOrderReturnApplyMapper.insert(mallOrderReturnApply);
    }

    @Override
    public MallOrderReturnApply selectByOrderId(Long orderId) {
        return  mallOrderReturnApplyMapper.selectByOrderId(orderId);
    }

    /**
     * 0:表示待处理,1:表示退货中  2:已完成  3:已拒绝
     * @param id
     * @return
     */
    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public int upadateStatusById(Long id,MallOrderReturnApply mallOrderReturnApply) {
//        Date now = new Date();
//        Integer status = returnApplyUpate.getStatus();
//        String  man = returnApplyUpate.getMan();
//        String note = returnApplyUpate.getNote();
        mallOrderReturnApply.setOrderReturnApplyId(id);
        if(mallOrderReturnApply.getStatus()==1) {
            //审核通过
            //更改订单的状态

            mallOrderService.updateOrderStatus(mallOrderReturnApply.getOrderId(), 4, null);
        }
        if(mallOrderReturnApply.getStatus()==2) {
            //收货成功
            //更改订单的状态为已取消状态

            mallOrderService.updateOrderStatus(mallOrderReturnApply.getOrderId(), 5, null);
            //收货成功,记得更改商品的库存
            Set<Long> goodIds = new HashSet<>();
            mallOrderItemService.selectByOrderId(mallOrderReturnApply.getOrderId()).stream().forEach(item->{
                productService.decreaseSkuById(item.getGoodsPropertyId(),item.getGoodsCount());
                MallGoodsProperty mallGoodsProperty = productService.getItem(item.getGoodsPropertyId()).getData();
                goodIds.add(mallGoodsProperty.getGoodsId());
            });

            //修改完商品属性的库存后,也要对商品的库存进行更新
            log.debug("goodIds"+goodIds);
            productService.updateProductStockByProperty( new ArrayList<>(goodIds));

        }
         return  mallOrderReturnApplyMapper.updateByPrimaryKey(mallOrderReturnApply);

    }


    @Override
    public MallOrderReturnApply selectById(Long id) {
        return  mallOrderReturnApplyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MallOrderReturnApply> selectList(ReturnApplyParams returnApplyParams, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        return  mallOrderReturnApplyMapper.selectList(returnApplyParams);
    }

//    @Override
//    public MallOrderReturnApply generateReturnApplyConfirm(Long orderId) {
//        //查询出订单
//        MallOrder order = mallOrderService.selectById(orderId);
//        MallUser user = userService.
//        //根据订单查询出用户的信息
//        return null;
//    }
}
