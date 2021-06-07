package com.springboot.cloud.StockWithRedis;



import com.springboot.cloud.pojo.MallSecondProperty;
import com.springboot.cloud.pojo.Stock;
import com.springboot.cloud.util.RedisPool;
import com.springboot.cloud.util.RedisPoolUtil;
import com.springboot.cloud.util.ScriptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * @author : lirisheng
 * @date : 2020/9/15
 **/
@Slf4j
@Component
public class StockWithRedis {

    @Autowired
    RedisPoolUtil redisPoolUtil;

    @Autowired
    RedisPool redisPool;

    /**
     * Redis 事务保证库存更新
     * 捕获异常后应该删除缓存
     */
    public  void updateStockWithRedis(Stock stock)throws  Exception {

        try(Jedis jedis=redisPool.getJedisPool().getResource()) {
            // 开始事务
            Transaction transaction = jedis.multi();
            // 事务操作
            redisPoolUtil.decr(RedisKeysConstant.STOCK_COUNT + stock.getId());
            redisPoolUtil.incr(RedisKeysConstant.STOCK_SALE + stock.getId());
            // 结束事务
            List<Object> list = transaction.exec();
        } catch (Exception e) {
            log.error("updateStock 获取 Jedis 实例失败：", e);
            throw  new RuntimeException("更新redis缓存失败");
        }
    }








    /**
     * 重置缓存
     */
//    public static void initRedisBefore() {
//        Jedis jedis = null;
//        try {
//            jedis = RedisPool.getJedis();
//            // 开始事务
//            Transaction transaction = jedis.multi();
//            // 事务操作
//            RedisPoolUtil.set(RedisKeysConstant.STOCK_COUNT + 1, "50");
//            RedisPoolUtil.set(RedisKeysConstant.STOCK_SALE + 1, "0");
//            RedisPoolUtil.set(RedisKeysConstant.STOCK_VERSION + 1, "0");
//            // 结束事务
//            List<Object> list = transaction.exec();
//        } catch (Exception e) {
//            log.error("initRedis 获取 Jedis 实例失败：", e);
//        } finally {
//            RedisPool.jedisPoolClose(jedis);
//        }
//    }

    public  void resetRedis(Stock stock) throws  Exception{

        try(Jedis jedis=redisPool.getJedisPool().getResource()){

            Transaction transaction  = jedis.multi();
            redisPoolUtil.set(RedisKeysConstant.STOCK_COUNT+stock.getId(),stock.getCount()+"");
            redisPoolUtil.set(RedisKeysConstant.STOCK_SALE+stock.getId(),stock.getSale()+"");
//            redisPoolUtil.set(RedisKeysConstant.STOCK_NAME+stock.getId(),stock.getName());
            List<Object> list = transaction.exec();
        }catch (Exception e){
            log.error("resetRedis 过程失败：", e.getMessage());
            throw  new RuntimeException("在重置Jedis时失败");
        }

    }

    public void  getSecondProperty(MallSecondProperty mallSecondProperty){
        Integer count  = Integer.parseInt(redisPoolUtil.get(RedisKeysConstant.STOCK_COUNT
                + mallSecondProperty.getGoodsPropertyId()));
        Integer saledCount = Integer.parseInt(redisPoolUtil.get(RedisKeysConstant.STOCK_SALE
                + mallSecondProperty.getGoodsPropertyId()));
        if(count>=0&&saledCount>=0){
            mallSecondProperty.setPropertyCount(count);
            mallSecondProperty.setPropertySelledCount(saledCount);
        }else{
            log.debug("redis中获取count saled失败");
        }

    }

    public String  secondKillWithRedis(Long id){
                // 解析 Lua 文件
            String script = ScriptUtil.getScript("secondKill.lua");
            System.out.println("script:"+script);
        System.out.println("id:"+id);
                // 计数限流
            return redisPoolUtil.processSecondKillLua(script,id);
    }



    /**
     * Redis 限流
     */
    public Boolean limit(Long lim) {
        // 解析 Lua 文件
        String script = ScriptUtil.getScript("limit.lua");
        // 计数限流
        Object result=redisPoolUtil.processLimitLua(script,lim);
        if (0 != (Long) result) {
            log.info("成功获取令牌");
            return true;
        }
        return false;

    }
}
