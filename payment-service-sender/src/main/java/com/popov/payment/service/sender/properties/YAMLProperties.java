package com.popov.payment.service.sender.properties;

import com.popov.payment.service.sender.properties.redis.Redis;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class YAMLProperties {
    private Redis redis;
}
