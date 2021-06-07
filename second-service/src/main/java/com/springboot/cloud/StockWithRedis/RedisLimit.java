package com.springboot.cloud.StockWithRedis;


import com.springboot.cloud.util.RedisPool;
import com.springboot.cloud.util.ScriptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * @author : lirisheng
 * @date : 2020/9/15
 **/
@Slf4j
@Component
public class RedisLimit {

    @Autowired
    RedisPool redisPool;

    private static final int FAIL_CODE = 0;

    private static Integer limit = 5000;


    /**
     * Redis 限流
     */
    public  Boolean limit() {
        Jedis jedis = null;
        Object result = null;
        try {
            // 获取 jedis 实例
            jedis = redisPool.getJedisPool().getResource();
            // 解析 Lua 文件
            String script = ScriptUtil.getScript("limit.lua");
            // 请求限流
            String key = String.valueOf(System.currentTimeMillis() / 1000);
            // 计数限流
            result = jedis.eval(script, Collections.singletonList(key), Collections.singletonList(String.valueOf(limit)));
            if (FAIL_CODE != (Long) result) {
                log.info("成功获取令牌");
                return true;
            }
        } catch (Exception e) {
            log.error("limit 获取 Jedis 实例失败：", e);
        } finally {
            jedis.close();
        }
        return false;
    }

    /**
     * 进行秒杀,即判断redis库存是否足够,如果足够,则进行库存删减
     * @param id
     * @return
     */
    public  String secondKillWithRedis(Long id) {
        Jedis jedis = null;
        String result = "";
        try {
            // 获取 jedis 实例
            jedis = redisPool.getJedisPool().getResource();
            // 解析 Lua 文件
            String script = ScriptUtil.getScript("secondKill.lua");
            // 请求限流

            // 计数限流
            result =(String) jedis.eval(script, Collections.singletonList(String.valueOf(1)), Collections.singletonList(String.valueOf(limit)));
            System.out.println("result"+result);
            if (!result.equals("")) {
                log.info("更改redis库存成功");
            }
        } catch (Exception e) {
            log.error("secondKill 获取 Jedis 实例失败：", e);
        } finally {
            jedis.close();
        }
        return result;
    }

    /**
     * 异步下单失败,回溯正确的数据
     * @param id
     * @return
     */
    public  String stockReturnWithRedis(Long id) {
        Jedis jedis = null;
        String result = "";
        try {
            // 获取 jedis 实例
            jedis = redisPool.getJedisPool().getResource();
            // 解析 Lua 文件
            String script = ScriptUtil.getScript("stockReturn.lua");
            // 请求限流

            // 计数限流
            result =(String) jedis.eval(script, Collections.singletonList(String.valueOf(1)), Collections.singletonList(String.valueOf(limit)));
            System.out.println("result"+result);
            if (!result.equals("")) {
                log.info("redis库存回溯成功");
            }
        } catch (Exception e) {
            log.error("secondKill 获取 Jedis 实例失败：", e);
        } finally {
            jedis.close();
        }
        return result;
    }
}
