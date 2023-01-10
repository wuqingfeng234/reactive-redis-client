package com.example.redis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {
    @GetMapping("test")
    public String test() {
        return "xxxx";
    }

    @GetMapping("/payment/webhook")
    public String webhook() {
        return "webhook";
    }
}
