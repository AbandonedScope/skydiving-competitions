package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.UserNotFoundException;
import by.grsu.skydiving.application.domain.model.auth.JwtAuthCredentials;
import by.grsu.skydiving.application.domain.model.auth.JwtToken;
import by.grsu.skydiving.application.domain.model.auth.JwtTokenGenerationSettings;
import by.grsu.skydiving.application.domain.model.auth.UserInfoForToken;
import by.grsu.skydiving.application.port.in.SignInUseCase;
import by.grsu.skydiving.application.port.out.FindUserInfoByLoginAndPasswordPort;
import by.grsu.skydiving.common.UseCase;
import by.grsu.skydiving.common.config.JwtSettings;
import lombok.RequiredArgsConstructor;


import static by.grsu.skydiving.application.domain.service.utils.SecretKeyUtils.getSigningKey;

@UseCase
@RequiredArgsConstructor
public class SingInService implements SignInUseCase {
    private final JwtSettings jwtSettings;
    private final FindUserInfoByLoginAndPasswordPort findPort;

    @Override
    public JwtAuthCredentials signIn(SignInQuery query) {
        UserInfoForToken userInfoForToken = findPort.findBy(query.login(), query.password())
                .orElseThrow(() -> new UserNotFoundException(query.login()));

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
