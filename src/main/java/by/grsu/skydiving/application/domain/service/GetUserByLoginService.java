package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.UserNotFoundException;
import by.grsu.skydiving.application.domain.model.UserInfo;
import by.grsu.skydiving.application.port.in.GetUserByLoginUseCase;
import by.grsu.skydiving.application.port.out.FindUserInfoByLoginAndPasswordPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetUserByLoginService implements GetUserByLoginUseCase {
    private final FindUserInfoByLoginAndPasswordPort findUserInfoPort;

    @Override
    public UserInfo getByLogin(String login) {
        return findUserInfoPort.findBy(login)
                .orElseThrow(() -> new UserNotFoundException(login));
    }
}
