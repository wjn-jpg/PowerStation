package com.ntdq.power_station.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import java.io.IOException;
import java.util.Properties;

/**
 * @author wang_ji_nian
 * @version 1.0
 */
public class RedisPoolFactory {

    /**
     * 日志
     */
    private final static Logger logger = LoggerFactory.getLogger(RedisPoolFactory.class);

    /**
     * 单例
     */
    private static RedisPoolFactory redisPoolFactory;

    /**
     * 最大存储Properties Key
     */
    private final static String MAX_TOTAL_PROPERTIES_KEY = "redis.maxTotal";

    /**
     * 最大等待连接中的数量的Propertieskey
     */
    private final static String MAX_IDLE_PROPERTIES_KEY = "redis.maxIdle";

    /**
     * 最大等待时间Properties Key.
     */
    private final static String MAX_WAIT_MILLIS_PROPERTIES_KEY = "redis.maxWaitMillis";

    /**
     * 是否测试Properties Key.
     */
    private final static String TEST_ON_BORROW_PROPERTIES_KEY = "redis.testOnBorrow";

    /**
     * Redis Host Properties Key.
     */
    private final static String HOST_PROPERTIES_KEY = "redis.host";

    /**
     * Redis Port Properties Key.
     */
    private final static String PORT_PROPERTIES_KEY = "redis.port";

    /**
     * Redis Timeout Properties Key.
     */
    private final static String TIMEOUT_PROPERTIES_KEY = "redis.timeout";
    /**
     * Redis Password Properties Key.
     */
    private final static String PASSWORD_PROPERTIES_KEY = "redis.password";

    /**
     * Redis Max Total Default Value。
     */
    private final static int MAX_TOTAL_PROPERTIES_DEFAULT_VALUE = 1000;
    /**
     * Redis Max Idele Default Value.
     */
    private final static int MAX_IDLE_PROPERTIES_DEFAULT_VALUE = 5;
    /**
     * Redis Max Wait Millis Default Value.
     */
    private final static long MAX_WAIT_MILLIS_PROPERTIES_DEFAULT_VALUE = 1000L;

    /**
     * redis配置文件
     */
    private final static String REDIS_PROPERTIES_FILE_PATH = "/redis.properties";

    private final static String HOST_PROPERTIES_DEFAULT_VALUE = "127.0.0.1";

    private final static int PORT_PROPERTIES_DEFAULT_VALUE = 6379;

    private final static int TIMEOUT_PROPERTIES_DEFAULT_VALUE = 0;

    private final static String PASSWORD_PROPERTIES_DEFAULT_VALUE = "";

    /**
     * jedis客户端
     */
    private JedisPool redisPool;

    public JedisPool getRedisPool() {
        return redisPool;
    }

    public static RedisPoolFactory getInstance(){
         if (redisPoolFactory==null){
             synchronized (RedisPoolFactory.class){
                 if (redisPoolFactory==null){
                     redisPoolFactory = new RedisPoolFactory();
                     return redisPoolFactory;
                 }
             }
         }
        return redisPoolFactory;
    }

    private RedisPoolFactory(){
       initRedisPool();
    }

    /**
     * 初始化redis连接池
     */
    private void initRedisPool(){
        logger.info("读取配置文件,初始化Redis连接池参数");
        try {
            Properties properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource(REDIS_PROPERTIES_FILE_PATH));
            JedisPoolConfig redisPoolConfig = new JedisPoolConfig();
            String strMAXToTal = properties.getProperty(MAX_TOTAL_PROPERTIES_KEY, String.valueOf(MAX_TOTAL_PROPERTIES_DEFAULT_VALUE));
            int maxTotal = NumberUtils.parseNumber(strMAXToTal, Integer.class);
            String strMaxIdle = properties.getProperty(MAX_IDLE_PROPERTIES_KEY, String.valueOf(MAX_IDLE_PROPERTIES_DEFAULT_VALUE));
            int maxIdle = NumberUtils.parseNumber(strMaxIdle, Integer.class);
            String strMaxWaitMillis = properties.getProperty(MAX_WAIT_MILLIS_PROPERTIES_KEY, String.valueOf(MAX_WAIT_MILLIS_PROPERTIES_DEFAULT_VALUE));
            long maxWaitMillis = NumberUtils.parseNumber(strMaxWaitMillis, Long.class);
            String strTestOnBorrow = properties.getProperty(TEST_ON_BORROW_PROPERTIES_KEY, String.valueOf(Boolean.FALSE));
            boolean testOnBorrow = Boolean.valueOf(strTestOnBorrow);
            redisPoolConfig.setMaxTotal(maxTotal);
            redisPoolConfig.setMaxIdle(maxIdle);
            redisPoolConfig.setMaxWaitMillis(maxWaitMillis);
            redisPoolConfig.setTestOnBorrow(testOnBorrow);
            String host = (String) properties.getOrDefault(HOST_PROPERTIES_KEY, HOST_PROPERTIES_DEFAULT_VALUE);
            String strPort = properties.getProperty(PORT_PROPERTIES_KEY, String.valueOf(PORT_PROPERTIES_DEFAULT_VALUE));
            int port = NumberUtils.parseNumber(strPort, Integer.class);
            String strTimeout = properties.getProperty(TIMEOUT_PROPERTIES_KEY, String.valueOf(TIMEOUT_PROPERTIES_DEFAULT_VALUE));
            int timeout = NumberUtils.parseNumber(strTimeout, Integer.class);
            String password = (String) properties.getOrDefault(PASSWORD_PROPERTIES_KEY, PASSWORD_PROPERTIES_DEFAULT_VALUE);

            /**
             * 如果密码不为空 并且 超时时间不为0
             */
            if (!StringUtils.isEmpty(password) && timeout!=0){
                redisPool = new JedisPool(redisPoolConfig,host,port,timeout,password);
                return;
            }
            /**
             * 如果密码为空 并且时间为0
             */
            if (StringUtils.isEmpty(password) && timeout==0){
                redisPool = new JedisPool(redisPoolConfig,host,port);
                return;
            }
            redisPool = new JedisPool(redisPoolConfig);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
