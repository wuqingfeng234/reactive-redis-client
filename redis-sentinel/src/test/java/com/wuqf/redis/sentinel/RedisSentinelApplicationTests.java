package com.wuqf.redis.sentinel;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class RedisSentinelApplicationTests {

    private final RedisService redisService;
    private final String NAME_KEY = "my-name";
    private final String NAME_VALUE = "wuqf";


    public RedisSentinelApplicationTests(RedisService redisService) {
        this.redisService = redisService;
    }

    @Test
    void contextLoads() {
        redisService.set(NAME_KEY, NAME_VALUE);
        String myname = redisService.get(NAME_KEY);
        assertThat(myname).isEqualTo(NAME_VALUE);
    }

}
