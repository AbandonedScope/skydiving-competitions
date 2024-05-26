package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.IncorrectPasswordException;
import by.grsu.skydiving.application.domain.exception.business.UserNotFoundException;
import by.grsu.skydiving.application.domain.model.auth.*;
import by.grsu.skydiving.application.port.in.SignInUseCase;
import by.grsu.skydiving.application.port.out.ExistsUserByLoginPort;
import by.grsu.skydiving.application.port.out.FindUserInfoPort;
import by.grsu.skydiving.common.UseCase;
import by.grsu.skydiving.common.config.JwtSettings;
import lombok.RequiredArgsConstructor;

import static by.grsu.skydiving.application.domain.service.utils.SecretKeyUtils.getSigningKey;

@UseCase
@RequiredArgsConstructor
public class SingInService implements SignInUseCase {
    private final JwtSettings jwtSettings;
    private final FindUserInfoPort findPort;
    private final ExistsUserByLoginPort existsByLoginPort;

    @Override
    public JwtAuthCredentials signIn(SignInQuery query) {
        String login = query.login();
        String password = query.password();

        if (!existsByLoginPort.existsByLogin(login)) {
            throw new UserNotFoundException(login);
        }

        UserInfoForToken userInfoForToken = findPort.findBy(login, password)
                .orElseThrow(() -> new IncorrectPasswordException(login));

        JwtToken jwtToken = JwtToken.of(buildSettings(userInfoForToken));

        return JwtAuthCredentials.builder()
                .accessToken(jwtToken)
                .userRole(userInfoForToken.role())
                .userId(userInfoForToken.userId())
                .build();
    }

    private JwtTokenGenerationSettings buildSettings(UserInfoForToken userInfoForToken) {
        return JwtTokenGenerationSettings.builder()
                .userId(userInfoForToken.userId())
                .subject(userInfoForToken.login())
                .userRole(userInfoForToken.role())
                .ttl(jwtSettings.ttl())
                .issuer(jwtSettings.issuer())
                .secretKey(getSigningKey(jwtSettings.secret()))
                .build();
    }
}
