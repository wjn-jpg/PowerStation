package com.ntdq.power_station.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@Component
public class RedisClient {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(RedisClient.class);

    private Jedis redis;

    public RedisClient(){
        JedisPool redisPool = RedisPoolFactory.getInstance().getRedisPool();
        this.redis = redisPool.getResource();
    }

    public void refresh(){
        logger.info("清空库中所有数据");
        this.redis.flushDB();
    }

    public Long increment(String key,int liveTime){
        logger.info("Redis 自增... ");
        Long result = this.redis.incr(key);
        if (liveTime>0){
            this.redis.expire(key,liveTime);
        }
        return result;
    }

    public String getKeyForStrValue(String key){
        return redis.get(key);
    }

    public void setKeyForStrValue(String key,String value){
        redis.set(key,value);
    }

    public boolean putHash(String keyPrefix, String keyValue, Map<String, String> fieldValueStrMap) {
        logger.info("putHash key:{}", keyPrefix + ":" + keyValue);
        String key = keyPrefix + ":" + keyValue;
        Long count = this.redis.hset(key, fieldValueStrMap);
        return count > 0;
    }

    public Map<String, String> getMapForHashKey(String keyPrefix, String keyValue) {
        String key = keyPrefix + ":" + keyValue;
        return this.redis.hgetAll(key);
    }


}
