package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.auth.UserCredentials;
import by.grsu.skydiving.application.domain.model.skydiver.FullName;

public interface GenerateUserCredentialsUseCase {
    UserCredentials generate(GenerateCredentialsCommand command);

    record GenerateCredentialsCommand(
        FullName fullName
    ) {
    }
}
