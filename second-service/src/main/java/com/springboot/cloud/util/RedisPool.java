package com.springboot.cloud.util;

import lombok.Getter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author : lirisheng
 * @date : 2020/9/15
 **/
@Component
@Getter
public class RedisPool implements InitializingBean {

   private  JedisPool jedisPool ;


   @Value("${jedisPoolConfig.host}")
   private String host;

    @Value("${jedisPoolConfig.port}")
    private  Integer port;

    @Value("${jedisPoolConfig.password}")
    private  String password;

    @Value("${jedisPoolConfig.maxTotal}")
    private  Integer maxTotal;

    @Value("${jedisPoolConfig.maxIdle}")
    private  Integer maxIdle;

    @Value("${jedisPoolConfig.maxWait}")
    private  Integer maxWait;

    @Value("${jedisPoolConfig.testOnBorrow}")
    private  Boolean testOnBorrow;

    @Value("${jedisPoolConfig.blockWhenExhausted}")
    private  Boolean blockWhenExhausted;

    @Value("${jedisPoolConfig.timeout}")
    private  Integer timeout;


    private void initPool(){
        JedisPoolConfig config= new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWait);
        config.setTestOnBorrow(testOnBorrow);
        config.setBlockWhenExhausted(blockWhenExhausted);
        jedisPool= new JedisPool(config,host,port,timeout,password);

    }

    @Override
    public void afterPropertiesSet() throws Exception {
       initPool();
    }

    private  RedisPool(){}

}
