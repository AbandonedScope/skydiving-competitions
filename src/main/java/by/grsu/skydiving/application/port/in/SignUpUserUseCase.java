package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.FullName;
import by.grsu.skydiving.application.domain.model.UserInfo;
import by.grsu.skydiving.application.domain.model.auth.UserRole;

public interface SignUpUserUseCase {
    UserInfo signUp(SignUpUserCommand command);

    record SignUpUserCommand(
            FullName fullName,
            UserRole role
    ) {
    }
}
