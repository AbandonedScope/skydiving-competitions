package by.grsu.skydiving.application.domain.exception.business;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(String login) {
        super("User with login '%s' was not found".formatted(login));
    }

    public UserNotFoundException(int id) {
        super("User with id '%d' was not found".formatted(id));
    }
}
