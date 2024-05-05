package by.grsu.skydiving.application.domain.model;

import by.grsu.skydiving.application.domain.model.auth.UserCredentials;
import by.grsu.skydiving.application.domain.model.auth.UserRole;
import lombok.Builder;

@Builder
public record UserInfo(
        FullName name,
        UserRole role,
        UserCredentials credentials
) {
}
