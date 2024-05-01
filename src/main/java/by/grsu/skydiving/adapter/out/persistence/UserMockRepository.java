package by.grsu.skydiving.adapter.out.persistence;

import by.grsu.skydiving.application.domain.model.FullName;
import by.grsu.skydiving.application.domain.model.UserInfo;
import by.grsu.skydiving.application.domain.model.auth.UserCredentials;
import by.grsu.skydiving.application.domain.model.auth.UserInfoForToken;
import by.grsu.skydiving.application.domain.model.auth.UserRole;
import by.grsu.skydiving.application.port.out.FindUserInfoByLoginAndPasswordPort;
import by.grsu.skydiving.common.PersistenceAdapter;
import org.springframework.context.annotation.Primary;

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

    @Override
    public Optional<UserInfo> findBy(String login) {
        return Optional.of(
                UserInfo.builder()
                        .name(new FullName("Maksim", "Makhanko", "Vitalevich"))
                        .credentials(new UserCredentials(login, "sdfsdf"))
                        .role(UserRole.SKYDIVER)
                        .build()
        );
    }
}
