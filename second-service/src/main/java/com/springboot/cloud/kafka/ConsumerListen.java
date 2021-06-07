package com.springboot.cloud.kafka;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.springboot.cloud.dto.OrderCommit;
import com.springboot.cloud.pojo.Stock;
import com.springboot.cloud.service.OrderService;
import com.springboot.cloud.service.SecondService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @auther Lirisheng
 * @date 6/12 19:17
 */
@Slf4j
@Component
public class ConsumerListen {

    private Gson gson = new GsonBuilder().create();

    @Autowired
    private OrderService orderService;


//    @KafkaListener(topics = "SECONDS-KILL-TOPIC")
//    public void listen(ConsumerRecord<String, String> record) {
//        OrderCommit orderCommit;
//        try {
//            Optional<?> kafkaMessage = Optional.ofNullable(record.value());
//            // Object -> String
//            String message = (String) kafkaMessage.get();
//            // 反序列化
//             orderCommit = gson.fromJson((String) message, OrderCommit.class);
//            // 创建订单
//
//            orderService.generateOrder(orderCommit);
//        }catch (Exception exception){
//            //更新失败,在orderCommit中恢复
//
//        }
//    }
}
