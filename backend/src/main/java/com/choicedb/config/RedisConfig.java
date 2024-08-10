package com.choicedb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisConfig {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void setKeyValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object getValue(String key) {
        Object value = redisTemplate.opsForValue().get(key);
        return value;
    }

}
