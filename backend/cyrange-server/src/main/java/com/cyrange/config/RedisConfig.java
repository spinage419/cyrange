package com.cyrange.config;

import com.cyrange.service.ChallengesService;
import com.cyrange.utils.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@Configuration
public class RedisConfig {
    @Autowired
    public ChallengesService challengesService;
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory factory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.addMessageListener((message, pattern) -> {
            String expiredKey = new String(message.getBody());
            String[] strings=expiredKey.split("|");
            String userid=strings[0];
            String id=strings[2];
            challengesService.deleteChallenge(Integer.parseInt(userid),Integer.parseInt(id));
        }, new PatternTopic("__keyevent@0__:expired"));
        return container;
    }
}
