package com.example.demo;

import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.core.Ordered;

import java.util.Map;

public class CustomEnvironmentRepository implements EnvironmentRepository, Ordered {
    private int order = Ordered.LOWEST_PRECEDENCE - 10;

    @Override
    public int getOrder() {
        return this.order;
    }

    @Override
    public Environment findOne(String application, String profile, String label) {
        Environment environment = new Environment(application, profile);
        environment.add(makeDefault(application, profile));

        return environment;
    }

    private PropertySource makeDefault(String application, String profile) {
        Map<String, String> source = new java.util.HashMap();
        source.put("custom.property", "it is default value");

        return new PropertySource(application + "-default", source);
    }
}
