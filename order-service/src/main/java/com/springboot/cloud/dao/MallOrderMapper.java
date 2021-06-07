package com.springboot.cloud.dao;


import com.springboot.cloud.dto.OrderAndItem;
import com.springboot.cloud.dto.OrderParam;
import com.springboot.cloud.entity.MallOrder;
import com.springboot.cloud.entity.MallOrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;
@Mapper
public interface MallOrderMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(MallOrder record);

    MallOrder selectByPrimaryKey(Long orderId);

    List<MallOrder> selectAll();

    int updateByPrimaryKey(MallOrder record);

    /**
     * 待发货
     * @return
     */
    @Update("UPDATE  mall_order SET order_status=#{orderStatus} ,  pay_time=#{data} " +
            " ,pay_type=1 WHERE  order_id=#{orderId} ")
    int updateOrderStatusToOne(@Param("orderId")Long orderId,
                               @Param("orderStatus")Integer orderStatus,
                               @Param("data") Date data);

    /**
     * 待收获
     * @return
     */
    @Update("UPDATE  mall_order SET order_status=#{orderStatus} ,  delivery_time=#{data} " +
            "  WHERE  order_id=#{orderId} ")
    int updateOrderStatusToTwo(@Param("orderId")Long orderId,
                               @Param("orderStatus")Integer orderStatus,
                               @Param("data")Date data);


    /**
     * 完成
     * @return
     */
    @Update("UPDATE  mall_order SET order_status=#{orderStatus} ,  receive_time=#{data} " +
            " WHERE  order_id=#{orderId} ")
    int updateOrderStatusToTree(@Param("orderId")Long orderId,
                                @Param("orderStatus")Integer orderStatus,
                                @Param("data")Date data);

    /**
     * 退货中,已取消
     */
    @Update("UPDATE  mall_order SET order_status=#{orderStatus}  WHERE  order_id=#{orderId} ")
    int  updateOrderStatusToFour(@Param("orderId")Long orderId,
                                                @Param("orderStatus")Integer orderStatus);

//    /**
//     * 已退货
//     * @return
//     */
//    @Update("UPDATE  mall_order SET order_status=#{orderStatus}  WHERE  order_id=#{orderId} ")
//    int updateOrderStatusToFive(@Param("orderId")Long orderId,
//                                @Param("orderStatus")Integer orderStatus);

    /**
     * 分页获取用户订单
     */
    @Select("SELECT * FROM  mall_order  WHERE  user_id=#{userId} AND order_status=#{status} ORDER By  create_time desc ")
   List<MallOrder> selectByUserIdAndStatus(@Param("userId") Long userId,@Param("status") Integer status);

    @Select("SELECT * FROM  mall_order  WHERE  user_id=#{userId} ORDER By  create_time desc ")
    List<MallOrder> selectByUserId(@Param("userId") Long userId);


    List<MallOrder> selectList(OrderParam orderParam);

}