package com.springboot.cloud.service;


import com.springboot.cloud.dto.OrderCommit;
import com.springboot.cloud.pojo.Stock;

/**
 * @author : lirisheng
 * @date : 2020/9/15
 **/

public interface SecondService {

    /**
     * 清空订单表
     */
//    int delOrderDBBefore();


    /**
     * redis秒杀成功后,在进行下单的操作(库存减1,创建订单)
     * @param id
     * @throws Exception
     */
//    void createOrderWithRedis(Long id) throws Exception;

    /**
     * redis秒杀成功后,使用异步下单,并直接返回响应给用户
     * @param orderCommit
     * @throws Exception
     */
//    void createOrderWithRedisAndKafaka(OrderCommit orderCommit) throws Exception;

    /**
     *下单操作:mysql中的商品库存减1,并生成
     * @param stock
     * @throws Exception
     */
//     void createOrder(Stock stock) throws Exception;

}
