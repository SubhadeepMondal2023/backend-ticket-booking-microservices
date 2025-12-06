package com.example.apigateway.controller;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/circuit-breaker")
public class CircuitBreakerTestController {

    @Autowired(required = false)
    private CircuitBreakerRegistry circuitBreakerRegistry;

    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getCircuitBreakerStatus() {
        Map<String, Object> response = new HashMap<>();
        
        if (circuitBreakerRegistry == null) {
            response.put("error", "CircuitBreakerRegistry not found");
            return ResponseEntity.ok(response);
        }

        try {
            CircuitBreaker circuitBreaker = circuitBreakerRegistry
                    .circuitBreaker("bookingServiceCircuitBreaker");
            
            CircuitBreaker.Metrics metrics = circuitBreaker.getMetrics();
            
            response.put("name", "bookingServiceCircuitBreaker");
            response.put("state", circuitBreaker.getState().toString());
            response.put("failureRate", String.format("%.2f%%", metrics.getFailureRate()));
            response.put("slowCallRate", String.format("%.2f%%", metrics.getSlowCallRate()));
            response.put("numberOfBufferedCalls", metrics.getNumberOfBufferedCalls());
            response.put("numberOfFailedCalls", metrics.getNumberOfFailedCalls());
            response.put("numberOfSuccessfulCalls", metrics.getNumberOfSuccessfulCalls());
            response.put("numberOfNotPermittedCalls", metrics.getNumberOfNotPermittedCalls());
            response.put("numberOfSlowCalls", metrics.getNumberOfSlowCalls());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Circuit breaker 'bookingServiceCircuitBreaker' not found: " + e.getMessage());
            return ResponseEntity.ok(response);
        }
    }
    
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllCircuitBreakers() {
        Map<String, Object> response = new HashMap<>();
        
        if (circuitBreakerRegistry == null) {
            response.put("error", "CircuitBreakerRegistry not found");
            return ResponseEntity.ok(response);
        }
        
        circuitBreakerRegistry.getAllCircuitBreakers().forEach(cb -> {
            Map<String, Object> details = new HashMap<>();
            CircuitBreaker.Metrics metrics = cb.getMetrics();
            
            details.put("state", cb.getState().toString());
            details.put("failureRate", String.format("%.2f%%", metrics.getFailureRate()));
            details.put("bufferedCalls", metrics.getNumberOfBufferedCalls());
            details.put("failedCalls", metrics.getNumberOfFailedCalls());
            details.put("successfulCalls", metrics.getNumberOfSuccessfulCalls());
            
            response.put(cb.getName(), details);
        });
        
        return ResponseEntity.ok(response);
    }
}