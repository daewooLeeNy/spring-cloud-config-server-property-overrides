package com.example.demo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * This project provides a Spring Boot auto configuration. If the "custom" Spring profile is added Spring Cloud Config Server automatically picks up the CustomEnvironmentRepository
 */
@Configuration
@Profile("custom")
public class CustomAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(CustomEnvironmentRepository.class)
    public CustomEnvironmentRepository customEnvironmentRepository() {
        return new CustomEnvironmentRepository();
    }
}
