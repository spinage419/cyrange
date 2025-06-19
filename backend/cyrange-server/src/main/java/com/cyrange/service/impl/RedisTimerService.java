package com.cyrange.service.impl;

import com.cyrange.config.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisTimerService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 启动计时器
     */
    public void startTimer(String userId, String id,long duration, TimeUnit unit) {
        redisTemplate.opsForValue().set(userId+"|"+id ,"active", duration, unit);
    }

    /**
     * 获取剩余时间
     */
    public long getRemainingTime(String userId) {
        return redisTemplate.getExpire(userId, TimeUnit.SECONDS);
    }

    /**
     * 查询计时器的状态
     */
    public boolean getStatus(String userId) {
        if (redisTemplate.opsForValue().get(userId)==null){
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * 删除计时器
     */
    public void deleteTimer(String userId) {
        redisTemplate.delete(userId);
    }


    /**
     * 延时计时器
     *
     * @param userId   用户ID
     * @param id       计时器ID
     * @param duration 延长时间
     * @param unit     时间单位
     * @return 是否延时成功
     */
    public boolean extendTimer(String userId, String id, long duration, TimeUnit unit) {
        String key = userId + "|" + id;
        if (redisTemplate.hasKey(key)) {
            redisTemplate.expire(key, duration, unit);
            return true;
        }
        return false;
    }
}