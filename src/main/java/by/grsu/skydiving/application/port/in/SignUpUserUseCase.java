package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.auth.UserAuthInfo;
import by.grsu.skydiving.application.domain.model.auth.UserRole;
import by.grsu.skydiving.application.domain.model.skydiver.FullName;

public interface SignUpUserUseCase {
    UserAuthInfo signUp(SignUpUserCommand command);

    record SignUpUserCommand(
        FullName fullName,
        UserRole role
    ) {
    }
}
