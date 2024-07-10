package com.project.schedule.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.elasticsearch.rest")
public record ESConfigurationProperties(
        String urisOne,
        String urisTwo,
        String username,
        String password
) {
}
