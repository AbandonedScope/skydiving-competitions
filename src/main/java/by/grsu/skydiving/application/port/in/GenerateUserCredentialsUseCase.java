package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.FullName;
import by.grsu.skydiving.application.domain.model.auth.UserCredentials;

public interface GenerateUserCredentialsUseCase {
    UserCredentials generate(GenerateCredentialsCommand command);

    record GenerateCredentialsCommand(
            FullName fullName
    ) {
    }
}
