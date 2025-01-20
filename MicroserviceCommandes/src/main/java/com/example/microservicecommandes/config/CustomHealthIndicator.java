package com.example.microservicecommandes.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        // Custom logic
        return Health.up().withDetail("Custom", "Health Check Passed").build();
    }
}