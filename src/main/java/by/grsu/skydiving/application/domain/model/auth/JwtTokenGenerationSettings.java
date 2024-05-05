package by.grsu.skydiving.application.domain.model.auth;

import lombok.Builder;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Duration;

@Builder
public record JwtTokenGenerationSettings(
        String issuer,
        String subject,
        int userId,
        UserRole userRole,
        Duration ttl,
        SecretKey secretKey
) {
}
