package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.auth.UserAuthInfo;
import by.grsu.skydiving.application.domain.model.auth.UserCredentials;
import by.grsu.skydiving.application.port.in.GenerateUserCredentialsUseCase;
import by.grsu.skydiving.application.port.in.GenerateUserCredentialsUseCase.GenerateCredentialsCommand;
import by.grsu.skydiving.application.port.in.SignUpUserUseCase;
import by.grsu.skydiving.application.port.out.SaveUserPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class SignUpUserService implements SignUpUserUseCase {
    private final SaveUserPort saveUserPort;
    private final GenerateUserCredentialsUseCase generateCredentialsUseCase;

    @Override
    public UserAuthInfo signUp(SignUpUserCommand command) {
        GenerateCredentialsCommand credentialsCommand = buildGenerateCredentialsCommand(command);
        UserCredentials credentials = generateCredentialsUseCase.generate(credentialsCommand);

        UserAuthInfo newUser = UserAuthInfo.builder()
            .name(command.fullName())
            .role(command.role())
            .credentials(credentials)
            .build();

        long userId = saveUserPort.save(newUser);

        return newUser.withUserId(userId);
    }

    private GenerateCredentialsCommand buildGenerateCredentialsCommand(SignUpUserCommand command) {
        return new GenerateCredentialsCommand(null, command.fullName());
    }
}
