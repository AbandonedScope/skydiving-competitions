package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.UserNotFoundException;
import by.grsu.skydiving.application.domain.model.auth.UserAuthInfo;
import by.grsu.skydiving.application.domain.model.auth.UserCredentials;
import by.grsu.skydiving.application.domain.model.common.UserInfo;
import by.grsu.skydiving.application.port.in.GenerateUserCredentialsUseCase;
import by.grsu.skydiving.application.port.in.GenerateUserCredentialsUseCase.GenerateCredentialsCommand;
import by.grsu.skydiving.application.port.in.RegenerateUserCredentialsUseCase;
import by.grsu.skydiving.application.port.out.FindUserInfoPort;
import by.grsu.skydiving.application.port.out.SaveUserPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RegenerateUserCredentialsService implements RegenerateUserCredentialsUseCase {
    private final FindUserInfoPort findUserInfoPort;
    private final SaveUserPort saveUserPort;
    private final GenerateUserCredentialsUseCase generateCredentialsUseCase;

    @Override
    public UserCredentials regenerate(long userId) {
        UserInfo userInfo = findUserInfoPort.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        GenerateCredentialsCommand credentialsCommand = new GenerateCredentialsCommand(userInfo.name());
        UserCredentials credentials = generateCredentialsUseCase.generate(credentialsCommand);

        UserAuthInfo userWithCredentials = UserAuthInfo.builder()
                .userId(userId)
                .name(userInfo.name())
                .role(userInfo.role())
                .credentials(credentials)
                .build();

        saveUserPort.save(userWithCredentials);

        return credentials;
    }
}
