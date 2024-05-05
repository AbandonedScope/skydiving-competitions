package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.UserNotFoundException;
import by.grsu.skydiving.application.domain.model.auth.UserAuthInfo;
import by.grsu.skydiving.application.port.in.GetUserByLoginUseCase;
import by.grsu.skydiving.application.port.out.FindUserInfoByLoginAndPasswordPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetUserByLoginService implements GetUserByLoginUseCase {
    private final FindUserInfoByLoginAndPasswordPort findUserInfoPort;

    @Override
    public UserAuthInfo getByLogin(String login) {
        return findUserInfoPort.findBy(login)
                .orElseThrow(() -> new UserNotFoundException(login));
    }
}
