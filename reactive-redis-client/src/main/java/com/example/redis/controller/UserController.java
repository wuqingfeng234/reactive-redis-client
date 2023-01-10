package com.example.redis.controller;

import com.example.redis.domain.User;
import com.example.redis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Mono<User> getUser(@RequestParam String userId) {
        return userService.getUser(userId);
    }

    @PostMapping
    public Mono<Boolean> saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
}
