package by.grsu.skydiving.application.domain.exception.domain;

public class TeamWithNameNotFoundException extends DomainException {

    public TeamWithNameNotFoundException(String teamName) {
        super("Team. Team with name '%s' was not found.".formatted(teamName));
    }
}
