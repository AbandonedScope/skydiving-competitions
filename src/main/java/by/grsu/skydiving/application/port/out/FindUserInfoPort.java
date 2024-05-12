package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.auth.UserAuthInfo;
import by.grsu.skydiving.application.domain.model.auth.UserInfoForToken;
import by.grsu.skydiving.application.domain.model.common.UserInfo;

import java.util.Optional;

public interface FindUserInfoPort {
    Optional<UserInfoForToken> findBy(String login, String password);

    Optional<UserAuthInfo> findBy(String login);

    Optional<UserInfo> findById(long userId);
}
