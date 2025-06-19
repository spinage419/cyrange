package com.cyrange.aspect;

import com.cyrange.annotation.RateLimit;
import com.cyrange.context.BaseContext;
import com.cyrange.exception.BaseException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class RateLimitAspect {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Around("@annotation(rateLimit)")
    public Object checkRateLimit(ProceedingJoinPoint joinPoint, RateLimit rateLimit) throws Throwable {
        // 获取用户唯一标识（根据实际情况实现）
        int userId= BaseContext.getCurrentId();

        String redisKey = "rate_limit:" + userId;

        // 原子性操作：检查是否存在并设置新值
        Boolean canOperate = redisTemplate.opsForValue().setIfAbsent(
                redisKey,
                "locked",
                rateLimit.cooldown(),
                TimeUnit.SECONDS
        );

        if (canOperate != null && !canOperate) {
            throw new BaseException("request too fase, You should wait at least 1 min.");
        }

        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            // 业务执行失败时删除限制
            redisTemplate.delete(redisKey);
            throw e;
        }
    }

}