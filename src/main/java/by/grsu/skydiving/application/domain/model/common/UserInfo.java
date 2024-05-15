package by.grsu.skydiving.application.domain.model.common;

import by.grsu.skydiving.application.domain.model.auth.UserRole;
import by.grsu.skydiving.application.domain.model.skydiver.FullName;
import lombok.With;

public record UserInfo(
        @With
        Long userId,
        FullName name,
        UserRole role
) {
}
