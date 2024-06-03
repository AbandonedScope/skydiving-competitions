package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.UserNotFoundException;
import by.grsu.skydiving.application.domain.model.common.UserInfo;
import by.grsu.skydiving.application.port.in.GetUserByIdUseCase;
import by.grsu.skydiving.application.port.out.FindUserInfoPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetUserByIdService implements GetUserByIdUseCase {
    private final FindUserInfoPort findUserInfoPort;

    @Override
    public UserInfo getUserById(long id) {
        return findUserInfoPort.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
    }
}
