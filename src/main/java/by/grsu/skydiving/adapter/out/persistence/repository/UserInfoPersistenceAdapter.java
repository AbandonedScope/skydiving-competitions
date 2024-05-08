package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.UserInfoEntity;
import by.grsu.skydiving.adapter.out.persistence.mapper.UserInfoMapper;
import by.grsu.skydiving.application.domain.model.auth.UserAuthInfo;
import by.grsu.skydiving.application.domain.model.auth.UserInfoForToken;
import by.grsu.skydiving.application.port.out.FindUserInfoByLoginAndPasswordPort;
import by.grsu.skydiving.application.port.out.SaveUserPort;
import by.grsu.skydiving.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserInfoPersistenceAdapter implements SaveUserPort,
        FindUserInfoByLoginAndPasswordPort {
    private final UserInfoJdbcRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserInfoMapper userInfoMapper;

    @Override
    public Optional<UserInfoForToken> findBy(String login, String password) {
        return repository.findByLogin(login)
                .filter(entity -> passwordEncoder.matches(password, entity.getPassword()))
                .map(userInfoMapper::toTokenInfo);
    }

    @Override
    public Optional<UserAuthInfo> findBy(String login) {
        return repository.findByLogin(login)
                .map(userInfoMapper::toDomain);
    }

    @Override
    public void save(UserAuthInfo userAuthInfo) {
        UserInfoEntity userInfoEntity = userInfoMapper.toEntity(userAuthInfo);

        userInfoEntity.setPassword(passwordEncoder.encode(userAuthInfo.credentials().password()));
        repository.save(userInfoEntity);
    }
}
