package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.auth.UserAuthInfo;

public interface GetUserByLoginUseCase {
    UserAuthInfo getByLogin(String login);
}
