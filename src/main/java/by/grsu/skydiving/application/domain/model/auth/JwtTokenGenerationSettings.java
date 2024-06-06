package by.grsu.skydiving.application.domain.model.auth;

import java.time.Duration;
import javax.crypto.SecretKey;
import lombok.Builder;

@Builder
public record JwtTokenGenerationSettings(
    String issuer,
    String subject,
    long userId,
    UserRole userRole,
    Duration ttl,
    SecretKey secretKey
) {
}
