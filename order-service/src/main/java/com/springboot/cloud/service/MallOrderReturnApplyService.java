package com.springboot.cloud.service;

import com.springboot.cloud.dto.ReturnApplyParams;
import com.springboot.cloud.entity.MallOrderReturnApply;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/23 11:46
 * @Version 1.0
 */
public interface MallOrderReturnApplyService {

    /**
     * 添加退货单
     */
    int insert(MallOrderReturnApply mallOrderReturnApply);

    /**
     * 根据order查看退货单
     */
    MallOrderReturnApply selectByOrderId(Long orderId);

    /**
     * 进行退货单的状态的更改
     */
    int upadateStatusById(Long id,MallOrderReturnApply mallOrderReturnApply);

    /**
     * id查看退货单
     * @param id
     * @return
     */
     MallOrderReturnApply selectById(Long id);

    /**
     * 多参数分页查询退货单
     */
    List<MallOrderReturnApply> selectList(ReturnApplyParams returnApplyParams ,Integer pageSize,Integer pageNum);

    /**
     * 根据订单id生成要填写的退货单
     */
//    MallOrderReturnApply generateReturnApplyConfirm(Long orderId);
}
