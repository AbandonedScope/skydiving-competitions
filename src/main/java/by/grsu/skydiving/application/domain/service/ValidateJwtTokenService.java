package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.auth.JwtToken;
import by.grsu.skydiving.application.domain.service.utils.SecretKeyUtils;
import by.grsu.skydiving.application.port.in.ValidateJwtTokenUseCase;
import by.grsu.skydiving.common.UseCase;
import by.grsu.skydiving.common.config.JwtSettings;
import lombok.RequiredArgsConstructor;

import javax.crypto.SecretKey;

import static by.grsu.skydiving.application.domain.service.utils.SecretKeyUtils.getSigningKey;

@UseCase
@RequiredArgsConstructor
public class ValidateJwtTokenService implements ValidateJwtTokenUseCase {
    private final JwtSettings jwtSettings;

    @Override
    public boolean isValid(String jwtToken) {
        SecretKey secretKey = getSigningKey(jwtSettings.secret());
        try {
            new JwtToken(jwtToken, secretKey);
        } catch (Exception ex) {
            return false;
        }

        return true;
    }
}
