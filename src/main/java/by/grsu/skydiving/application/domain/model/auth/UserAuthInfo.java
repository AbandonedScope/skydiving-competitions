package by.grsu.skydiving.application.domain.model.auth;

import by.grsu.skydiving.application.domain.model.skydiver.FullName;
import lombok.Builder;
import lombok.With;

@Builder
public record UserAuthInfo(
    @With
    Long userId,
    FullName name,
    UserRole role,
    UserCredentials credentials
) {
}
