/*
 * Redis cache configuration to connect to remote redis instance (not in memory).
 * App implementation: when we call endpoint to get all tasks it caches the result,
 * if client call update/create or delete operation the cache is evicted to show
 * actual data and then renew the cache result.
 */

package com.todoapi.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.time.Duration;

@EnableCaching
@Configuration
public class RedisConfig {
    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10)) // Set cache entry expiration time to 10 minutes
                .disableCachingNullValues(); // Disable caching of null values
    }
}
