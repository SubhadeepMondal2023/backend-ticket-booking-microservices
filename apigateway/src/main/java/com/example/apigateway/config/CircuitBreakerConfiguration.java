package com.example.apigateway.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CircuitBreakerConfiguration {

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(5))
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(8)
                .minimumNumberOfCalls(4)
                .permittedNumberOfCallsInHalfOpenState(2)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                .recordExceptions(Exception.class)
                .build();

        CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);
        
        // Pre-register the circuit breaker
        registry.circuitBreaker("bookingServiceCircuitBreaker", config);
        
        return registry;
    }
}