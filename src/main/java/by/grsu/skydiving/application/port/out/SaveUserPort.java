package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.UserInfo;

public interface SaveUserPort {
    void save(UserInfo userInfo);
}
