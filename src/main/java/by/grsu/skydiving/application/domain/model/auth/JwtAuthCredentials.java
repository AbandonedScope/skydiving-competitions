package by.grsu.skydiving.application.domain.model.auth;

import lombok.Builder;

@Builder
public record JwtAuthCredentials(
        int userId,
        JwtToken accessToken,
        UserRole userRole
) {
}
