package by.grsu.skydiving.adapter.in.web.response;

import by.grsu.skydiving.application.domain.model.auth.UserRole;

public record UserInfoResponse(
    long id,
    String firstName,
    String secondName,
    String patronymic,
    UserRole role
) {
}
