package com.cyrange.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.rmi.ServerRuntimeException;
import java.util.Map;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String HASH_KEY_PREFIX = "exam:";
    private static final String TODAY_CHALLENGES_KEY = "todayChallenges";
    private static final String SUCCESSFUL_BREACHES_KEY = "successfulBreaches";
    private static final String ONLINE_USERS_KEY = "onlineUsers";

    public void save(String userId, String questionId, String containerId, String flag) {
        String hashKey = HASH_KEY_PREFIX + userId + ":" + questionId;
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        hashOps.put(hashKey, "containerId", containerId);
        hashOps.put(hashKey, "flag", flag);
    }

    public Map<String, String> get(String userId, String questionId) {
        String hashKey = HASH_KEY_PREFIX + userId + ":" + questionId;
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        return hashOps.entries(hashKey);
    }

    public void delete(String userId, String questionId) {
        String hashKey = HASH_KEY_PREFIX + userId + ":" + questionId;
        redisTemplate.delete(hashKey);
    }

    public void saveStatus(String userId,String questionId){
        redisTemplate.opsForValue().set(userId, questionId);
    }

    public String getStatus(String userId){
        return  (String) redisTemplate.opsForValue().get(userId);
    }

    public void deleteStatus(String userId){
        redisTemplate.delete(userId);
    }

    public void setTodayChallenges(int value) {
        redisTemplate.opsForValue().set(TODAY_CHALLENGES_KEY, value);
    }

    public String getTodayChallenges() {
        return redisTemplate.opsForValue().get(TODAY_CHALLENGES_KEY).toString();
    }

    public void setSuccessfulBreaches(int value) {
        redisTemplate.opsForValue().set(SUCCESSFUL_BREACHES_KEY, value);
    }

    public String getSuccessfulBreaches() {
        return  redisTemplate.opsForValue().get(SUCCESSFUL_BREACHES_KEY).toString();
    }

    public void setOnlineUsers(int value) {
        redisTemplate.opsForValue().set(ONLINE_USERS_KEY, value);
    }

    public String getOnlineUsers() {
        return redisTemplate.opsForValue().get(ONLINE_USERS_KEY).toString();
    }
}