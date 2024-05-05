package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.UserInfo;
import by.grsu.skydiving.application.domain.model.auth.UserInfoForToken;

import java.util.Optional;

public interface FindUserInfoByLoginAndPasswordPort {
    Optional<UserInfoForToken> findBy(String login, String password);

    Optional<UserInfo> findBy(String login);
}
