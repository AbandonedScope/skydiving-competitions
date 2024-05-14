package by.grsu.skydiving.application.domain.model.auth;

import lombok.Builder;

@Builder
public record UserInfoForToken(
        long userId,
        UserRole role,
        String login
) {
    public static UserInfoForToken of(UserAuthInfo userAuthInfo) {
        return UserInfoForToken.builder()
                .userId(userAuthInfo.userId())
                .role(userAuthInfo.role())
                .login(userAuthInfo.credentials().login())
                .build();
    }
}
