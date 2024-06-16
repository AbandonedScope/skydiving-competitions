package by.grsu.skydiving.application.domain.exception.business;

public class IncorrectPasswordException extends BusinessException {
    public IncorrectPasswordException(String login) {
        super("Неверный пароль для пользователя с логином: '%s'.".formatted(login));
    }
}
