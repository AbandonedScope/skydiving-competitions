package by.grsu.skydiving.adapter.out.persistence;

import by.grsu.skydiving.application.domain.model.UserInfo;
import by.grsu.skydiving.application.domain.model.auth.UserInfoForToken;
import by.grsu.skydiving.application.domain.model.auth.UserRole;
import by.grsu.skydiving.application.port.out.FindUserInfoByLoginAndPasswordPort;
import by.grsu.skydiving.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserInfoJdbcRepository implements FindUserInfoByLoginAndPasswordPort {
    private final UserInfoRepository userInfoRepository;

    @Override
    public Optional<UserInfoForToken> findBy(String login, String password) {
        return userInfoRepository.findByLoginAndPassword(login, password)
                .map(userInfoEntity -> UserInfoForToken.builder()
                        .userId(userInfoEntity.getUserInfoId())
                        .role(UserRole.valueOf(userInfoEntity.getRole()))
                        .login(login)
                        .build()
                );
    }

    @Override
    public Optional<UserInfo> findBy(String login) {
        return Optional.empty();
    }
}
