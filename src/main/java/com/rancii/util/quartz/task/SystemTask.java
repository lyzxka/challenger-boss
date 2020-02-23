package com.rancii.util.quartz.task;

import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


/**
 * Created by hantw on 2018/1/26.
 * todo: 系统定时任务
 */
@Component("systemTask")
public class SystemTask {
    private static Log log = LogFactory.get();

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


}
