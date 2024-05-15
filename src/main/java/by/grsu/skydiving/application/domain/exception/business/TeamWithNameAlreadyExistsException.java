package by.grsu.skydiving.application.domain.exception.business;

public class TeamWithNameAlreadyExistsException extends BusinessException {
    public TeamWithNameAlreadyExistsException(String teamName) {
        super("Team with name '%s' already exists.".formatted(teamName));
    }
}
