package com.rancii.util;

import com.rancii.config.RedisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: zhanglei
 * @Date: 2018/8/14 13:56
 * @Description:
 */
@Component
public class RedisUtil {

    private static final Logger log = LoggerFactory.getLogger(RedisUtil.class);

    private static int TIMEOUT = 10000;

    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;

    private static JedisPool jedisPool = null;

    @Autowired
    private  RedisConfig redisConfig;


    /**
     * 初始化Redis连接池
     */
    private  void init(){
        try {
            //在高版本的jedis jar包，比如2.8.2，我们在使用中发现使用JedisPoolConfig时，没有setMaxActive和setMaxWait属性了，这是因为高版本中官方废弃了此方法，用以下两个属性替换。
            //maxActive  ==>  maxTotal
            //maxWait    ==>  maxWaitMillis
            String ADDR = redisConfig.getIp();
            int PORT =  redisConfig.getPort();
            int MAX_ACTIVE = redisConfig.getMaxActive();
            int MAX_IDLE = redisConfig.getMaxIdle();
            int MAX_WAIT = redisConfig.getMaxWait();
            log.info("redis端口"+redisConfig.getPort());
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
        } catch (Exception e) {
            log.error("初始化redis连接池异常:",e);
        }
    }

    /**
     * 获取Jedis实例
     * @return
     */
    public synchronized  Jedis getJedis() {
        if (jedisPool != null) {
            Jedis resource = jedisPool.getResource();
            return resource;
        } else {
            init();
            return  jedisPool.getResource();
        }
    }

    /**
     * 释放jedis资源
     * @param jedis
     */
    public  void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }

    /**
     * 锁账户
     * @param id
     * @return
     */
    public boolean lockAccount(String id){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            while (true){
                if (jedis!=null){
                    Long setnx = jedis.setnx("LK" + id, id);
                    if (setnx>0){
                        log.info("锁定账户成功！mid="+id);
                        return true;
                    }
                }else{
                    jedis = getJedis();
                }
            }
        }catch (Exception e){
            log.error("锁账户异常:",e);
        }finally {
            if (jedis!=null){
                returnResource(jedis);
            }
        }
        return false;
    }

    /**
     * 解锁账户
     * @param id
     * @return
     */
    public void unLockAccount(String id){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (jedis!=null){
                jedis.del("LK"+id);
                log.info("解锁账户成功！mid="+id);
            }
        }catch (Exception e){
            log.error("解锁账户异常:",e);
        }finally {
            if (jedis!=null){
                returnResource(jedis);
            }
        }
    }

    /**
     * 存队列
     * @return
     */
    public void leftPush(String key, Object obj){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (jedis!=null){
                jedis.lpush(key.getBytes(), new byte[][]{SerializeUtil.serialize(obj)});
            }
        }catch (Exception e){
            log.error("存入队列异常:",e);
        }finally {
            if (jedis!=null){
                returnResource(jedis);
            }
        }
    }

    /**
     * 取队列
     * @param key
     */
    public Object rightPop(String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (jedis!=null){
                long size = jedis.llen(key.getBytes()).longValue();
                if (size > 0L) {
                    byte[] temp = jedis.rpop(key.getBytes());
                    if (temp != null) {
                        Object obj = SerializeUtil.deserialize(temp);
                        return obj;
                    }
                }
            }
        }catch (Exception e){
            log.error("取队列异常:",e);
        }finally {
            if (jedis!=null){
                returnResource(jedis);
            }
        }
        return null;
    }

}
