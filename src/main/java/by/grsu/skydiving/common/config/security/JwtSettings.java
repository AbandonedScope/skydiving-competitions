package by.grsu.skydiving.common.config.security;

import java.time.Duration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtSettings(
    String secret,
    Duration ttl,
    String issuer
) {
}
