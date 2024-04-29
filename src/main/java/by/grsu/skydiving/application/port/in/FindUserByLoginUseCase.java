package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.UserInfo;

import java.util.Optional;

public interface FindUserByLoginUseCase {
    Optional<UserInfo> findByLogin(String login);
}
