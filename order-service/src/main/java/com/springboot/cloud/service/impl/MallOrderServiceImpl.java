package com.springboot.cloud.service.impl;

import com.github.pagehelper.PageHelper;
import com.springboot.cloud.common.entity.MallGoodsProperty;
import com.springboot.cloud.common.entity.user.MallUserAddress;
import com.springboot.cloud.dao.MallOrderMapper;
import com.springboot.cloud.dto.*;
import com.springboot.cloud.entity.*;
import com.springboot.cloud.service.*;
import com.springboot.cloud.util.CommonPage;
import com.springboot.cloud.util.OrderUtils;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: lirisheng
 * @Date: 2021/2/23 11:44
 * @Version 1.0
 */
@Service
@Slf4j
public class MallOrderServiceImpl implements MallOrderService {
    @Autowired
    MallOrderMapper mallOrderMapper;

    @Autowired
    MallOrderItemService mallOrderItemService;

    @Autowired
    MallShoppingCartItemService mallShoppingCartItemService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    MallOrderAddressService mallOrderAddressService;

    @Autowired
    MallCouponHistoryService mallCouponHistoryService;

    @Autowired
    MallCouponService mallCouponService;

    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public int generateOrder(OrderCommit orderCommit) {
        //判断库存是否足够
        List<MallOrderItem> mallOrderItems = orderCommit.getMallOrderItems();
        List<MallGoodsProperty> mallGoodsProperties = new ArrayList<>();
        //存储goodInfo的id,用于之后的更新
        Set<Long> goodIds = new HashSet<>();
        mallOrderItems.forEach(item->{
            log.debug("goodPropertyId:"+item.getGoodsPropertyId());
            MallGoodsProperty mallGoodsProperty = productService.getItem(item.getGoodsPropertyId()).getData();
            if(mallGoodsProperty.getStockNum()-item.getGoodsCount()<0){
                new Exception("库存不足");
            }
            mallGoodsProperty.setStockNum(mallGoodsProperty.getStockNum()-item.getGoodsCount());
            mallGoodsProperty.setSelledStockNum(mallGoodsProperty.getSelledStockNum()+item.getGoodsCount());
            goodIds.add(mallGoodsProperty.getGoodsId());
            mallGoodsProperties.add(mallGoodsProperty);
         });
        //生成order
         String orderSn = OrderUtils.getOrderCode(orderCommit.getUserId().intValue());
         orderCommit.setOrderNo(orderSn);
         MallOrder order = orderCommit;
         mallOrderMapper.insert(order);

        //生成orderItem
        mallOrderItems.forEach(item-> {
            item.setOrderId(order.getOrderId());
        });
        mallOrderItemService.insertList(mallOrderItems);

        mallGoodsProperties.forEach(item->{

            productService.update(item.getGoodsPropertyId(),item);

        });
        //修改完商品属性的库存后,也要对商品的库存进行更新
        log.debug("goodIds"+goodIds);
        productService.updateProductStockByProperty( new ArrayList<>(goodIds));

        //生成收获地址
        MallUserAddress mallUserAddress  =  userService.select(orderCommit.getOrderAddressId()).getData();
        MallOrderAddress mallOrderAddress = generateOrderAddress(mallUserAddress);
        mallOrderAddress.setOrderId(order.getOrderId());
        mallOrderAddressService.insertOrderAdress(mallOrderAddress);

        //注意:还要更改用户领取优惠劵的状态
        //获取根据优惠劵的id,
        if(orderCommit.getCouponHistoryId()!=null){
            MallCouponHistory mallCouponHistory= mallCouponHistoryService.select(orderCommit.getCouponHistoryId());
            mallCouponHistory.setUseStatus(1);
            mallCouponHistory.setUseTime(new Date());
            mallCouponHistory.setOrderId(order.getOrderId());
            mallCouponHistory.setOrderSn(order.getOrderNo());
            mallCouponHistoryService.update(mallCouponHistory.getCouponHistoryId(),mallCouponHistory);
            //更改优惠劵的使用数量信息
            mallCouponService.updateUserCount(mallCouponHistory.getCouponId());
        }


        if(orderCommit.getShopCateIds()!=null){
            //如果shopCateList有值,则进行删除
            mallShoppingCartItemService.delete(orderCommit.getUserId(),orderCommit.getShopCateIds());
        }


        return 1;
    }

    MallOrderAddress generateOrderAddress(MallUserAddress mallUserAddress){
        MallOrderAddress mallOrderAddress = new MallOrderAddress();
        mallOrderAddress.setCityName(mallUserAddress.getCityName());
        mallOrderAddress.setProvinceName(mallUserAddress.getProvinceName());
        mallOrderAddress.setRegionName(mallUserAddress.getRegionName());
        mallOrderAddress.setDetailAddress(mallUserAddress.getDetailAddress());
        mallOrderAddress.setUserName(mallUserAddress.getUserName());
        mallOrderAddress.setUserPhone(mallUserAddress.getUserPhone());
        return  mallOrderAddress;
    }

    @Override
    public OrderCommit generateConfirmOrder(List<Long> cartIds) {
        //生成Order
        //根据传入的购物车Id获取购物车信息
        Iterator<MallShoppingCartItem> iterator = mallShoppingCartItemService.getListByCartIds(cartIds).iterator();
        List<MallOrderItem> orderItems= new ArrayList<>();
        OrderCommit order =new OrderCommit();
        BigDecimal totalAmount=new BigDecimal("0");
        while(iterator.hasNext()){
            MallShoppingCartItem mallShoppingCartItem = iterator.next();
            MallOrderItem mallOrderItem  =new MallOrderItem();
            totalAmount=totalAmount.add(changeShoppingCartToOrderItem(mallShoppingCartItem,mallOrderItem));
            orderItems.add(mallOrderItem);
        }
        order.setTotalAmount(totalAmount);
//        order.setFreightAmount(new BigDecimal("0"));
        order.setExtraInfo("");
        order.setMallOrderItems(orderItems);
        order.setShopCateIds(cartIds);
//        order.setCouponAmount(new BigDecimal("0"));
//        BigDecimal payAmount = order.getTotalAmount().add(order.getFreightAmount()).subtract(order.getCouponAmount());
//        order.setPayAmount(payAmount);
        return order;
    }

    @Override
    public OrderCommit generateConfirmOrder(MallOrderItem mallOrderItem) {
        List<MallOrderItem> orderItems= new ArrayList<>();
        BigDecimal totalPrice=mallOrderItem.getSellingPrice().multiply(new BigDecimal(mallOrderItem.getGoodsCount()+""));
        mallOrderItem.setGoodsTotalPrice(totalPrice);
        orderItems.add(mallOrderItem);
        OrderCommit order =new OrderCommit();
        order.setExtraInfo("");
        order.setMallOrderItems(orderItems);
        order.setTotalAmount(totalPrice);
//        order.setCouponAmount(new BigDecimal("0"));
//        BigDecimal payAmount = order.getTotalAmount().add(order.getFreightAmount()).subtract(order.getCouponAmount());
//        order.setPayAmount(payAmount);
        return  order;
    }

    BigDecimal changeShoppingCartToOrderItem(MallShoppingCartItem mallShoppingCartItem, MallOrderItem mallOrderItem){
        mallOrderItem.setGoodsPropertyId(mallShoppingCartItem.getGoodsPropertyId());
        mallOrderItem.setGoodsName(mallShoppingCartItem.getGoodsName());
        mallOrderItem.setGoodsInfo(mallShoppingCartItem.getGoodsInfo());
        mallOrderItem.setGoodsCoverImg(mallShoppingCartItem.getGoodsCoverImg());
        mallOrderItem.setSellingPrice(mallShoppingCartItem.getGoodsPrice());
        mallOrderItem.setGoodsCount(mallShoppingCartItem.getGoodsCount());
        BigDecimal totalPrice=mallOrderItem.getSellingPrice().multiply(new BigDecimal(mallOrderItem.getGoodsCount()+""));
        mallOrderItem.setGoodsTotalPrice(totalPrice);
        return  totalPrice;
    }

    @Override
    public Integer paySuccess(Long orderId, Integer payType) {
        return null;
    }

    @Override
    public Integer cancelTimeOutOrder() {
        return null;
    }

    @Override
    public void cancelOrder(Long orderId) {

    }

    @Override
    public void sendDelayMessageCancelOrder(Long orderId) {

    }

    @Override
    public void confirmReceiveOrder(Long orderId) {

    }

    @Override
    public List<MallOrder> selectList(OrderParam orderParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);

        return  mallOrderMapper.selectList(orderParam);
    }

    /**
     * 0:未支付  1:待发货 2:待收获 3:完成  4:退货中 5:取消
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<OrderAndItem> listByUserId(Long userId,Integer status, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<OrderAndItem> orderAndItems = new ArrayList<>();
        List<MallOrder> mallOrders;
        if(status==-1) {
            //查询所有订单
            mallOrders=mallOrderMapper.selectByUserId(userId);
        }else if(status==0){
            //获取未支付订单
            mallOrders=mallOrderMapper.selectByUserIdAndStatus(userId,status);
        }else if(status==1){
            //获取 待发货订单
            mallOrders=mallOrderMapper.selectByUserIdAndStatus(userId,status);
        }else if(status==2){
            //获取待收货订单和退货中订单
            mallOrders=mallOrderMapper.selectByUserIdAndStatus(userId,status);
            mallOrders.addAll(mallOrderMapper.selectByUserIdAndStatus(userId,status+2));
        }else {
            //获取完成和取消订单
            mallOrders=mallOrderMapper.selectByUserIdAndStatus(userId,status);
            mallOrders.addAll(mallOrderMapper.selectByUserIdAndStatus(userId,status+2));
        }
        //查找订单详情
        log.debug("mallOrders:"+mallOrders);
        Iterator<MallOrder> iterator=mallOrders.iterator();

        while (iterator.hasNext()){
            MallOrder mallOrder = iterator.next();
            OrderAndItem orderAndItem=changeMallOrderToOrderAndItem(mallOrder);
             orderAndItem.setMallOrderItems(mallOrderItemService.selectByOrderId(mallOrder.getOrderId()));
             orderAndItems.add(orderAndItem);
        }

        return orderAndItems;
    }

    OrderAndItem changeMallOrderToOrderAndItem(MallOrder mallOrder){
        OrderAndItem orderAndItem = new OrderAndItem();
        orderAndItem.setOrderId(mallOrder.getOrderId());
        orderAndItem.setCouponAmount(mallOrder.getCouponAmount());
        orderAndItem.setCreateTime(mallOrder.getCreateTime());
        orderAndItem.setUpdateTime(mallOrder.getUpdateTime());
        orderAndItem.setDeliveryTime(mallOrder.getDeliveryTime());
        orderAndItem.setReceiveTime(mallOrder.getReceiveTime());
        orderAndItem.setExtraInfo(mallOrder.getExtraInfo());
        orderAndItem.setFreightAmount(mallOrder.getFreightAmount());
        orderAndItem.setIsDeleted(mallOrder.getIsDeleted());
        orderAndItem.setOrderNo(mallOrder.getOrderNo());
        orderAndItem.setOrderStatus(mallOrder.getOrderStatus());
        orderAndItem.setPayAmount(mallOrder.getPayAmount());
        orderAndItem.setUserId(mallOrder.getUserId());
        orderAndItem.setTotalAmount(mallOrder.getTotalAmount());
        orderAndItem.setPayStatus(mallOrder.getPayStatus());
        orderAndItem.setPayTime(mallOrder.getPayTime());
        orderAndItem.setPayType(mallOrder.getPayType());
       return  orderAndItem;
    }

    @Override
    public void deleteOrder(Long orderId) {

    }

    @Override
    public List<MallOrder> list(MallOrderQueryParam queryParam, Integer pageSize, Integer pageNum) {
        return null;
    }

    @Override
    public int close(List<Long> ids, String note) {
        return 0;
    }

    @Override
    public int delete(List<Long> ids) {
        return 0;
    }

    @Override
    public int updateNote(Long id, String note, Integer status) {
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateOrderStatus(Long orderId, Integer orderStatus, Date date) {
        int count=0;
        if(orderStatus==1){
            //未支付->待发货

            count=mallOrderMapper.updateOrderStatusToOne(orderId,orderStatus,date);
        }else if(orderStatus ==2){
            //代发货->已发货

            count=mallOrderMapper.updateOrderStatusToTwo(orderId,orderStatus,date);
        }else if(orderStatus==3){
            //已发货->完成
            count=mallOrderMapper.updateOrderStatusToTree(orderId,orderStatus,date);
        }else if(orderStatus==4){
            // * -> 退货中
            count=mallOrderMapper.updateOrderStatusToFour(orderId,orderStatus);
        }else if(orderStatus==5){
            //退货中->已取消
            count=mallOrderMapper.updateOrderStatusToFour(orderId,orderStatus);
        }else {
            return 0;
        }

        return 1;
    }

    @Override
    public OrderAndItem selectById(Long id) {
        MallOrder mallOrder = mallOrderMapper.selectByPrimaryKey(id);
        List<MallOrderItem> mallOrderItems= mallOrderItemService.selectByOrderId(id);
        OrderAndItem orderAndItem = changeMallOrderToOrderAndItem(mallOrder);
        orderAndItem.setMallOrderItems(mallOrderItems);
        return orderAndItem;
    }
}
