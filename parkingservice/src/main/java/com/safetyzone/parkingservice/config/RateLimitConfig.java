package com.safetyzone.parkingservice.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * Configuration for rate limit
 */
@Configuration
public class RateLimitConfig {

    @Value("${bucket.capacity}")
    private Integer buckCapacity;
    @Value("${bucket.token-size}")
    private Integer refillToken;
    @Value("${bucket.refill-interval}")
    private Integer refillInterval;

    @Bean
    public Bucket bucket() {
        Bucket bucket;
        Bandwidth limit = Bandwidth.classic(buckCapacity, Refill.greedy(refillToken, Duration.ofMinutes(refillInterval)));
        bucket = Bucket.builder().addLimit(limit).build();
        return bucket;
    }
}
