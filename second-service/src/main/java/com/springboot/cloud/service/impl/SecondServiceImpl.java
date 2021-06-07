package com.springboot.cloud.service.impl;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.springboot.cloud.StockWithRedis.RedisLimit;
import com.springboot.cloud.StockWithRedis.StockWithRedis;
import com.springboot.cloud.dto.OrderCommit;
import com.springboot.cloud.pojo.Stock;
import com.springboot.cloud.service.SecondService;
import com.springboot.cloud.util.RedisPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author : lirisheng
 * @date : 2020/9/15
 **/
@Slf4j
@Service
public class SecondServiceImpl implements SecondService {





    @Autowired
    private StockWithRedis stockWithRedis;

    @Autowired
    RedisLimit redisLimit;

    @Autowired
    private RedisPoolUtil redisPoolUtil;

//    @Autowired
//    private KafkaTemplate<String,String> kafkaTemplate;

//    @Value("${spring.kafka.template.default-topic}")
//    private String kafkaTopic;

    private Gson gson=new GsonBuilder().create();


//    @Override
//    public int delOrderDBBefore() {
//        return orderMapper.delOrderDBBefore();
//    }




//    @Override
//    public void createOrderWithRedis(Long id) throws Exception {
//        //判断redis中是否有库存足够,如果,则扣除
//        // String result=stockWithRedis.secondKillWithRedis(id);
//        String result=redisLimit.secondKillWithRedis(id);
//        if(result.equals("")){
//            throw  new RuntimeException("库存不足,秒杀失败");
//        }
//
//        //扣除库存,创建订单
//        Stock stock = new Stock();
//        stock.setName(result);
//        stock.setId(id);
//        createOrder(stock);
//
//    }




//    @Override
//    public void createOrderWithRedisAndKafaka(OrderCommit orderCommit) throws Exception {
//
//        //判断redis中是否有库存足够,如果,则扣除
//        String result=redisLimit.secondKillWithRedis(orderCommit.getMallOrderItems().get(0).getGoodsPropertyId());
//        if(result.equals("")){
//            throw  new RuntimeException("库存不足,秒杀失败");
//        }
//        //扣除库存,创建订单,并该任务放在Kafka任务中
////        Stock stock = new Stock();
////        stock.setName(result);
////        stock.setId(id);
//        try {
//            kafkaTemplate.send(kafkaTopic,gson.toJson(orderCommit));
//        }catch (Exception exception){
//            //异步下单失败
//            //把redis中
//        }
//
//    }







//    @Override
//    public void createOrderWithLimitAndRedisAndKafka(int sid) throws Exception {
//        // 校验库存
//        Stock stock = checkStockWithRedis(sid);
//        // 下单请求发送至 kafka，需要序列化 stock
//        kafkaTemplate.send(kafkaTopic, gson.toJson(stock));
//        log.info("消息发送至 Kafka 成功");
//    }

//    @Override
//    public int consumerTopicToCreateOrderWithKafka(Stock stock) throws Exception {
//        // 乐观锁更新库存和 Redis
//        saleStockOptimsticWithRedis(stock);
//        int res = createOrder(stock);
//        if (res == 1) {
//            log.info("Kafka 消费 Topic 创建订单成功");
//        } else {
//            log.info("Kafka 消费 Topic 创建订单失败");
//        }
//
//        return res;
//    }

    /**
     * Redis 校验库存
     *
     * @param sid
     */
//    private Stock checkStockWithRedis(int sid) throws Exception {
//        Integer count = Integer.parseInt(RedisPoolUtil.get(RedisKeysConstant.STOCK_COUNT + sid));
//        Integer sale = Integer.parseInt(RedisPoolUtil.get(RedisKeysConstant.STOCK_SALE + sid));
//        Integer version = Integer.parseInt(RedisPoolUtil.get(RedisKeysConstant.STOCK_VERSION + sid));
//        if (count < 1) {
//            log.info("库存不足");
//            throw new RuntimeException("库存不足 Redis currentCount: " + sale);
//        }
//        Stock stock = new Stock();
//        stock.setId((long)sid);
//        stock.setCount((long)count);
//        stock.setSale((long)sale);
//        stock.setVersion((long)version);
//        // 此处应该是热更新，但是在数据库中只有一个商品，所以直接赋值
//        stock.setName("手机");
//
//        return stock;
//    }
//    private Stock checkStockWithRedis(int sid) throws  Exception {
//        Integer count = Integer.parseInt(redisPoolUtil.get(RedisKeysConstant.STOCK_COUNT+sid));
//        Integer verson = Integer.parseInt(redisPoolUtil.get(RedisKeysConstant.STOCK_VERSION+sid));
//        String name=redisPoolUtil.get(RedisKeysConstant.STOCK_NAME+sid);
//        if(count<1){
//            throw new RuntimeException("库存不足");
//        }
//        Stock stock = new Stock();
//        stock.setId((long)sid);
//        stock.setVersion((long)verson);
//        stock.setName(name);
//        return  stock;
//    }

//    /**
//     * 更新数据库和 DB
//     */
//    private void saleStockOptimsticWithRedis(Stock stock) throws Exception {
//        int res = stockService.updateStockByOptimistic(stock);
//        if (res == 0) {
//            throw new RuntimeException("并发更新库存失败");
//        }
//        // 更新 Redis
//        StockWithRedis.updateStockWithRedis(stock);
//    }

//    @Transactional(rollbackFor = Exception.class,
//                   isolation = Isolation.READ_UNCOMMITTED)
//    public void saleStockOptimsticWithRedis(Stock stock) throws Exception{
//        //更新数据库
//        int res=stockService.updateStockByOptimistic(stock);
//        if(res==0){
//            throw  new RuntimeException("库存不足,更新失败");
//        }
//        //创建订单
//        createOrder(stock);
//        //创建缓存
//        stockWithRedis.updateStockWithRedis(stock);
//
//    }


//    /**
//     * 校验库存
//     */
//    private Stock checkStock(int sid) throws Exception {
//        Stock stock = stockService.getStockById(sid);
//        if (stock.getCount() < 1) {
//            throw new RuntimeException("库存不足");
//        }
//        return stock;
//    }

//    /**
//     * 扣库存
//     */
//    private int saleStock(Stock stock) {
//        stock.setSale(stock.getSale() + 1);
//        stock.setCount(stock.getCount() - 1);
//        return stockService.updateStockById(stock);
//    }
//
//    /**
//     * 乐观锁扣库存
//     */
//    private void saleStockOptimstic(Stock stock) throws Exception {
//        int count = stockService.updateStockByOptimistic(stock);
//        if (count == 0) {
//            throw new RuntimeException("并发更新库存失败");
//        }
//    }

    /**
     * 扣除mysql的库存并创建订单
     */

//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void createOrder(Stock stock) throws Exception {
//
//        StockOrder order = new StockOrder();
//        order.setSid(stock.getId());
//        order.setName(stock.getName());
//        order.setCreateTime(new Date());
//        //扣除库存
//        stockService.updateStockById(stock.getId());
//        //下订单
//        orderMapper.insertSelective(order);
//
//    }
}
