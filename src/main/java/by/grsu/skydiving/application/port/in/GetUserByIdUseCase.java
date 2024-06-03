package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.common.UserInfo;

public interface GetUserByIdUseCase {
    UserInfo getUserById(long id);
}
