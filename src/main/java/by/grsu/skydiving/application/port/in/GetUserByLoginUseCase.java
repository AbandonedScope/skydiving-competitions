package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.UserInfo;

public interface GetUserByLoginUseCase {
    UserInfo getByLogin(String login);
}
