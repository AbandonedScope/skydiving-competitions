package by.grsu.skydiving.application.domain.model.auth;

import by.grsu.skydiving.application.domain.model.skydiver.FullName;
import lombok.Builder;

@Builder
public record UserAuthInfo(
    Long userId,
    FullName name,
    UserRole role,
    UserCredentials credentials
) {
}
