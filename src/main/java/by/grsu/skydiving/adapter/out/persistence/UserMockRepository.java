package by.grsu.skydiving.adapter.out.persistence;

import by.grsu.skydiving.application.domain.model.auth.UserInfoForToken;
import by.grsu.skydiving.application.domain.model.auth.UserRole;
import by.grsu.skydiving.application.port.out.FindUserInfoByLoginAndPasswordPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public class UserMockRepository implements FindUserInfoByLoginAndPasswordPort {
    @Override
    public Optional<UserInfoForToken> findBy(String login, String password) {
        return Optional.of(
                UserInfoForToken.builder()
                        .userId(1)
                        .login(login)
                        .role(UserRole.SKYDIVER)
                        .build()
        );
    }
}
