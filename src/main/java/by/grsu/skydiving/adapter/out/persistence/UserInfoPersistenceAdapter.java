package by.grsu.skydiving.adapter.out.persistence;

import by.grsu.skydiving.adapter.out.persistence.entity.UserInfoEntity;
import by.grsu.skydiving.adapter.out.persistence.mapper.UserInfoMapper;
import by.grsu.skydiving.adapter.out.persistence.repository.UserInfoJdbcRepository;
import by.grsu.skydiving.application.domain.model.auth.UserAuthInfo;
import by.grsu.skydiving.application.domain.model.auth.UserInfoForToken;
import by.grsu.skydiving.application.domain.model.common.UserInfo;
import by.grsu.skydiving.application.port.out.ExistsUserByLoginPort;
import by.grsu.skydiving.application.port.out.FindUserInfoPort;
import by.grsu.skydiving.application.port.out.SaveUserPort;
import by.grsu.skydiving.common.PersistenceAdapter;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserInfoPersistenceAdapter implements SaveUserPort,
        FindUserInfoPort, ExistsUserByLoginPort {
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
    public Optional<UserInfo> findById(long userId) {
        return repository.findByUserId(userId)
                .map(userInfoMapper::toDomain);
    }

    @Override
    public void save(UserAuthInfo userAuthInfo) {
        UserInfoEntity userInfoEntity = userInfoMapper.toEntity(userAuthInfo);

        userInfoEntity.setPassword(passwordEncoder.encode(userAuthInfo.credentials().password()));
        repository.save(userInfoEntity);
    }

    @Override
    public boolean existsByLogin(String login) {
        return repository.existsByLogin(login);
    }
}
