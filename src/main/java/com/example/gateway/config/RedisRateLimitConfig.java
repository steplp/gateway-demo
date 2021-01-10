package com.example.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @ClassName：RedisRateLimtConfig
 * @Description：
 * @Author：Peng Liu
 * @Date：2020/12/4
 * @Version：V1.0
 **/
@Configuration
public class RedisRateLimitConfig {

    @Bean(name = "apiKeyResolver")
    public KeyResolver apiKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }
}
