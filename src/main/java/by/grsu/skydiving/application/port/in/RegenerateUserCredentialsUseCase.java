package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.auth.UserCredentials;

public interface RegenerateUserCredentialsUseCase {
    UserCredentials regenerate(long userId);
}
