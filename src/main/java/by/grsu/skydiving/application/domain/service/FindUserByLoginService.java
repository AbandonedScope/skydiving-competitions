package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.UserInfo;
import by.grsu.skydiving.application.port.in.FindUserByLoginUseCase;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class FindUserByLoginService implements FindUserByLoginUseCase {
    @Override
    public Optional<UserInfo> findByLogin(String login) {
        return Optional.empty();
    }
}
