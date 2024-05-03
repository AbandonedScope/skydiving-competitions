package by.grsu.skydiving.application.domain.model.auth;

import by.grsu.skydiving.application.domain.exception.domain.ValidationException;
import lombok.Builder;

import java.util.HashMap;
import java.util.Map;

import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.*;

@Builder
public record UserCredentials(
        String login,
        String password
) {
    public UserCredentials {
        validate(login, password);
    }

    private void validate(String login, String password) {
        Map<String, String > errors = new HashMap<>();

        if (login == null || login.isBlank()) {
            errors.put(LOGIN_NULL_OR_BLANK_KEY, LOGIN_IS_NULL_OR_BLANK_MESSAGE);
        }

        if (password == null || password.isBlank()) {
            errors.put(PASSWORD_NULL_OR_BLANK_KEY, PASSWORD_IS_NULL_OR_BLANK_MESSAGE);
        }

        if (!errors.isEmpty()) {
            throw ValidationException.builder()
                    .errors(errors)
                    .build();
        }
    }
}
