package by.grsu.skydiving.adapter.out.persistence;

import by.grsu.skydiving.adapter.out.persistence.entity.UserInfoEntity;
import by.grsu.skydiving.adapter.out.persistence.mapper.UserInfoMapper;
import by.grsu.skydiving.application.domain.model.UserInfo;
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
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserInfoMapper userInfoMapper;

    @Override
    public Optional<UserInfoForToken> findBy(String login, String password) {
        return userInfoRepository.findByLogin(login)
                .filter(entity -> passwordEncoder.matches(password, entity.getPassword()))
                .map(userInfoMapper::toTokenInfo);
    }

    @Override
    public Optional<UserInfo> findBy(String login) {
        return userInfoRepository.findByLogin(login)
                .map(userInfoMapper::toDomain);
    }

    @Override
    public void save(UserInfo userInfo) {
        UserInfoEntity userInfoEntity = userInfoMapper.toEntity(userInfo);

        userInfoEntity.setPassword(passwordEncoder.encode(userInfo.credentials().password()));
        userInfoRepository.save(userInfoEntity);
    }
}
