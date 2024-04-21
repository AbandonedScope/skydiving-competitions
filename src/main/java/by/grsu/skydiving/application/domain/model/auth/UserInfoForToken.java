package by.grsu.skydiving.application.domain.model.auth;

import lombok.Builder;

@Builder
public record UserInfoForToken(
        int userId,
        UserRole role,
        String login
) {
}
