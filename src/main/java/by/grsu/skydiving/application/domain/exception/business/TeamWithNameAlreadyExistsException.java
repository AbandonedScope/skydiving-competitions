package by.grsu.skydiving.application.domain.exception.business;

public class TeamWithNameAlreadyExistsException extends BusinessException {
    public TeamWithNameAlreadyExistsException(String teamName) {
        super("Команда с названием '%s' уже существует.".formatted(teamName));
    }
}
