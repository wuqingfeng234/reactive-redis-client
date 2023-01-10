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
    private ReactiveValueOperations<String, User> opsForValue;
    private static final String USER_KEY_PREFIX = "user::";

    public UserService(ReactiveRedisTemplate<String, User> reactiveRedisTemplate) {
        this.opsForValue = reactiveRedisTemplate.opsForValue();
    }

    public Mono<User> getUser(String userId) {
        String key = USER_KEY_PREFIX + userId;
        return opsForValue.get(key)
                .switchIfEmpty(getUserFromFirebase(userId));
    }

    public Mono<Boolean> saveUser(User user) {
        log.info("save user .");
        String key = USER_KEY_PREFIX + user.getId();
        return opsForValue.set(key, user);
    }

    private Mono<User> getUserFromFirebase(String userId) {
        log.info("get user from firebase ,user id is {} .", userId);
        return Mono.just(new User(userId, "mock", 8L, "met"))
                .doOnSuccess(this::saveUser)
                .retry(3);
    }
}
