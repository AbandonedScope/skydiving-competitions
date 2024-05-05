package by.grsu.skydiving.application.domain.model.common;

import by.grsu.skydiving.application.domain.model.auth.UserRole;
import by.grsu.skydiving.application.domain.model.skydiver.FullName;

public record UserInfo(
        Integer userId,
        FullName name,
        UserRole role
) {
}
