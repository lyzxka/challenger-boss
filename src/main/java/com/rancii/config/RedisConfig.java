/**
 * @ClassName QiniuConfig
 * @Description TODO
 * @Author Administrator
 * @Date 2018/8/20 0020 16:12
 * @Version 1.0
 **/
package com.rancii.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RedisConfig {
    private static final Logger log = LoggerFactory.getLogger(RedisConfig.class);


    @Value("${spring.redis.host}")
    private  String ip;
    @Value("${spring.redis.port}")
    private  int port;
    @Value("${spring.redis.pool.max-active}")
    private  int maxActive;
    @Value("${spring.redis.pool.max-idle}")
    private  int maxIdle;
    @Value("${spring.redis.pool.max-wait}")
    private  int maxWait;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    /*
    *
    *      String ADDR = rb.getString("redis.ip");
            int PORT = Integer.valueOf(rb.getString("redis.port"));
            int MAX_ACTIVE = Integer.valueOf(rb.getString("redis.maxActive"));
            int MAX_IDLE = Integer.valueOf(rb.getString("redis.maxIdle"));
            int MAX_WAIT = Integer.valueOf(rb.getString("redis.maxWait"));
    * */
}
