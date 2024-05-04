package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.application.domain.model.auth.UserInfoForToken;
import by.grsu.skydiving.application.domain.model.auth.UserRole;
import by.grsu.skydiving.application.port.out.FindUserInfoByLoginAndPasswordPort;
import by.grsu.skydiving.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserInfoPersistenceAdapter implements FindUserInfoByLoginAndPasswordPort {
    private final UserInfoJdbcRepository userInfoJdbcRepository;

    @Override
    public Optional<UserInfoForToken> findBy(String login, String password) {
        return userInfoJdbcRepository.findByLoginAndPassword(login, password)
                .map(userInfoEntity -> UserInfoForToken.builder()
                        .userId(userInfoEntity.getUserInfoId())
                        .role(UserRole.valueOf(userInfoEntity.getRole()))
                        .login(login)
                        .build()
                );
    }
}
