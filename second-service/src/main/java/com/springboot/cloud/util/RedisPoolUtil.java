package com.springboot.cloud.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.List;

/**
 * @author : lirisheng
 * @date : 2020/9/15
 **/
@Slf4j
@Component
public class RedisPoolUtil {

    @Autowired
    RedisPool redisPool;
    /**
     * 设置 key - value 值
     *
     * @param key
     * @param value
     */
    public String set(String key, String value) {

        String result = null;

        try(Jedis jedis=redisPool.getJedisPool().getResource()) {
            result = jedis.set(key, value);
        } catch (Exception e) {
            log.error("set key:{} value:{} error", key, value, e);
        }
        return result;
    }

    /**
     * 获取 key - value 值
     *
     * @param key
     */
    public  String get(String key) {

        String result = null;

        try(Jedis jedis=redisPool.getJedisPool().getResource()) {

            result = jedis.get(key);
        } catch (Exception e) {
            log.error("get key:{} error", key, e);
        }
        return result;
    }

    /**
     * 删除 key - value 值
     *
     * @param key
     */
    public  Long del(String key) {

        Long result = null;
        try (Jedis jedis=redisPool.getJedisPool().getResource()){

            result = jedis.del(key);
        } catch (Exception e) {
            log.error("del key:{} error", key, e);
        }
        return result;
    }

    /**
     * key - value 自增
     */
    public  Long incr (String key) {

        Long result = null;
        try(Jedis jedis=redisPool.getJedisPool().getResource()) {

            result = jedis.incr(key);
        } catch (Exception e) {
            log.error("listGet key:{} error", key, e);
        }
        return result;
    }

    /**
     * key - value 自减
     */
    public Long decr (String key) {

        Long result = null;
        try(Jedis jedis=redisPool.getJedisPool().getResource()) {

            result = jedis.decr(key);
        } catch (Exception e) {
            log.error("listGet key:{} error", key, e);
        }
        return result;
    }

    /**
     * List - get 操作
     */
    public  List<String> listGet(String key) {

        List<String> result = null;
        try (Jedis jedis=redisPool.getJedisPool().getResource()){

            result = jedis.lrange(key, 0, -1);
        } catch (Exception e) {
            log.error("listGet key:{} error", key, e);
        }
        return result;
    }

    /**
     * List - put 操作
     */
    public Long listPut(String key, String count, String sale, String version) {

        Long result = null;
        try (Jedis jedis=redisPool.getJedisPool().getResource()){

            result = jedis.lpush(key, version, sale, count);
        } catch (Exception e) {
            log.error("listPut key:{} error", key, e);
        }
        return result;
    }

    public String  processSecondKillLua(String script,Long id){
        Object result=null;
        try(Jedis jedis = redisPool.getJedisPool().getResource()) {
            result = jedis.eval(script, Collections.singletonList(id + ""), null);

        }catch (Exception exception){
            log.error("redis在进行秒杀的过程出现异常");
            log.error("message:"+exception.getMessage());
            log.error("message:"+exception.getCause());
        }
        System.out.println("result"+(String)result);
        return (String)result;
    }

    public Long  processLimitLua(String script,Long limit){
        Long result=0L;
        try(Jedis jedis = redisPool.getJedisPool().getResource()){
            String key = String.valueOf(System.currentTimeMillis() / 1000);
            // 计数限流
            result = (Long) jedis.eval(script, Collections.singletonList(key), Collections.singletonList(String.valueOf(limit)));

        }catch (Exception exception){
            log.error("redis在进行秒杀的过程出现异常");
        }
        return result;
    }

}
