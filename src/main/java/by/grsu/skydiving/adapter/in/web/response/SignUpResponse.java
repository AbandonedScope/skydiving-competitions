package by.grsu.skydiving.adapter.in.web.response;

import by.grsu.skydiving.application.domain.model.auth.UserRole;

public record SignUpResponse(
    Long id,
    String firstName,
    String secondName,
    String patronymic,
    UserRole role,
    String login,
    String password
) {
}
