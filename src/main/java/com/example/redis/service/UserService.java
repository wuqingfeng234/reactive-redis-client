package com.example.redis.service;

import com.example.redis.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class UserService {
    private ReactiveRedisTemplate reactiveRedisTemplate;
    private ReactiveValueOperations<String, User> opsForValue;

    public UserService(ReactiveRedisTemplate reactiveRedisTemplate) {
        this.reactiveRedisTemplate = reactiveRedisTemplate;
        this.opsForValue = reactiveRedisTemplate.opsForValue();
    }

    public Mono<User> getUser(String userId) {
        String key = "user::" + userId;
        return opsForValue.get(key)
                .switchIfEmpty(getUserFromFirebase(userId));
    }

    public Mono<Boolean> saveUser(User user) {
        log.info("save user .");
        String key = "user::" + user.getId();
        return opsForValue.set(key, user);
    }

    private Mono<User> getUserFromFirebase(String userId) {
        String key = "user::" + userId;
        log.info("get user from firebase ,user id is {} .", userId);
        return Mono.just(new User(userId, "mock", 8L, "met"))
                .flatMap(this::saveUser)
                .flatMap(status -> opsForValue.get(key));
    }
}
