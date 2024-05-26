package by.grsu.skydiving.application.domain.exception.business;

public class IncorrectPasswordException extends BusinessException {
    public IncorrectPasswordException(String login) {
        super("Incorrect password for user with login '%s'.".formatted(login));
    }
}
