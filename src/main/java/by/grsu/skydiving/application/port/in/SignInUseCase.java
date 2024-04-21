package by.grsu.skydiving.application.port.in;


import by.grsu.skydiving.application.domain.model.auth.JwtAuthCredentials;
import lombok.Builder;

public interface SignInUseCase {
    JwtAuthCredentials signIn(SignInQuery query);

    @Builder
    record SignInQuery(
            String login,
            String password
    ) {
    }
}
