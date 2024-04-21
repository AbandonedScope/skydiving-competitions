package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.auth.UserInfoForToken;

import java.util.Optional;

public interface FindUserInfoByLoginAndPasswordPort {
    Optional<UserInfoForToken> findBy(String login, String password);
}
