package by.grsu.skydiving.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = "jwt")
public record JwtSettings(
        String secret,
        Duration ttl,
        String issuer
) {
}
