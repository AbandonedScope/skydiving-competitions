package by.grsu.skydiving.adapter.in.web.dto;

import by.grsu.skydiving.application.domain.model.auth.UserRole;

public record SignInResponse(
        int userId,
        UserRole userRole,
        String accessToken
) {
}
