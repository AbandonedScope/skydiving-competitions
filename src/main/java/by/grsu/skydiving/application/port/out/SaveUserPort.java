package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.auth.UserAuthInfo;

public interface SaveUserPort {
    long save(UserAuthInfo userAuthInfo);
}
