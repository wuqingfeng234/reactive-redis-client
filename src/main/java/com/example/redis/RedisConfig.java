package com.example.redis;

import com.example.redis.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
class RedisConfig {

    @Bean
    public ReactiveRedisTemplate<String, User> reactiveRedisTemplate(ReactiveRedisConnectionFactory connectionFactory) {
        RedisSerializationContext.RedisSerializationContextBuilder<String, User> builder = RedisSerializationContext.newSerializationContext();
        RedisSerializationContext<String, User> context = builder.key(StringRedisSerializer.UTF_8)
                .value(new Jackson2JsonRedisSerializer<>(User.class))
                .hashKey(StringRedisSerializer.UTF_8)
                .hashValue(StringRedisSerializer.UTF_8)
                .build();
        return new ReactiveRedisTemplate<>(connectionFactory, context);
    }
}