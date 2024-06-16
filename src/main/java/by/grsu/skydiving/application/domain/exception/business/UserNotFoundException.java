package by.grsu.skydiving.application.domain.exception.business;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(String login) {
        super("Пользователь с логином '%s' не был найден.".formatted(login));
    }

    public UserNotFoundException(long id) {
        super("Пользователь с id '%d' не был найден.".formatted(id));
    }
}
