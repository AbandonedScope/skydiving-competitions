package by.grsu.skydiving.adapter.in.web.request;

import by.grsu.skydiving.application.domain.model.auth.UserRole;

public record SignUpRequest(
    String firstName,
    String secondName,
    String patronymic,
    UserRole role
) {
}
