package com.springboot.cloud.service;


import com.springboot.cloud.dto.MallOrderQueryParam;
import com.springboot.cloud.dto.OrderAndItem;
import com.springboot.cloud.dto.OrderCommit;
import com.springboot.cloud.dto.OrderParam;
import com.springboot.cloud.entity.MallOrder;
import com.springboot.cloud.entity.MallOrderItem;
import com.springboot.cloud.util.CommonPage;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: lirisheng
 * @Date: 2021/2/23 11:43
 * @Version 1.0
 */
public interface MallOrderService {
    /**
     * 根据提交信息生成订单
     * @param orderCommit
     */
    int generateOrder(OrderCommit orderCommit);


    /**
     * 根据购物车id生成订单信息
     */
    OrderCommit generateConfirmOrder(List<Long> cartIds);

    /**
     * 根据商品信息生成确认订单信息
     */
    OrderCommit generateConfirmOrder(MallOrderItem mallOrderItem);

    /**
     * 支付成功后的回调
     */
    @Transactional
    Integer paySuccess(Long orderId, Integer payType);

    /**
     * 自动取消超时订单
     */
    @Transactional
    Integer cancelTimeOutOrder();

    /**
     * 取消单个超时订单
     */
    @Transactional
    void cancelOrder(Long orderId);

    /**
     * 发送延迟消息取消订单
     */
    void sendDelayMessageCancelOrder(Long orderId);

    /**
     * 确认收货
     */
    void confirmReceiveOrder(Long orderId);

    /**
     * 分页获取用户订单
     */
    List<OrderAndItem> listByUserId(Long userId,Integer status, Integer pageSize, Integer pageNum);

    /**
     * 后台管理系统多参数分页查询用户订单
     * @param orderParam
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<MallOrder> selectList(OrderParam orderParam, Integer pageSize, Integer pageNum);



    /**
     * listAll
     */
    /**
     * 用户根据订单ID删除订单
     */
    void deleteOrder(Long orderId);

    /**
     * 订单查询
     */
    List<MallOrder> list(MallOrderQueryParam queryParam, Integer pageSize, Integer pageNum);

    /**
     * 批量发货
     */
//    @Transactional
//    int delivery(List<OmsOrderDeliveryParam> deliveryParamList);

    /**
     * 批量关闭订单
     */
    @Transactional
    int close(List<Long> ids, String note);

    /**
     * 批量删除订单
     */
    int delete(List<Long> ids);


    /**
     * 修改订单费用信息
     */
//     @Transactional
//     int updateMoneyInfo(OmsMoneyInfoParam moneyInfoParam);

    /**
     * 修改订单备注
     */
    @Transactional
    int updateNote(Long id, String note, Integer status);

    /**
     * 改变订单的状态,例如支付,已发货,完成,退货中,退货完成
     */
    int updateOrderStatus(Long orderId, Integer orderStatus, Date date);


    /**
     *根据订单id和获取订单
     */
    OrderAndItem selectById(Long id);
}
