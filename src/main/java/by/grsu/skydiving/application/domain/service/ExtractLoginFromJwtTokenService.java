package by.grsu.skydiving.application.domain.service;

import static by.grsu.skydiving.application.domain.service.utils.SecretKeyUtils.getSigningKey;

import by.grsu.skydiving.application.domain.model.auth.JwtToken;
import by.grsu.skydiving.application.port.in.ExtractLoginFromJwtTokenUseCase;
import by.grsu.skydiving.common.UseCase;
import by.grsu.skydiving.common.config.security.JwtSettings;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ExtractLoginFromJwtTokenService implements ExtractLoginFromJwtTokenUseCase {
    private final JwtSettings jwtSettings;

    @Override
    public String extractLogin(String token) {
        SecretKey secretKey = getSigningKey(jwtSettings.secret());
        JwtToken jwtToken = new JwtToken(token, secretKey);

        return jwtToken.extractLogin();
    }
}
