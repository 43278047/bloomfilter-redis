package com.zzyy.study.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @auther zzyy
 * @create 2020-09-15 14:47
 */
@Configuration
public class RedisConfig
{

    @Bean
    public RedisTemplate<String,Serializable> redisTemplate(LettuceConnectionFactory connectionFactory)
    {
        RedisTemplate<String,Serializable> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        return redisTemplate;
    }

    @Bean
    public RedissonClient redisson()
    {
        Config config = new Config();

        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(0);

        return (RedissonClient) Redisson.create(config);
    }
}
