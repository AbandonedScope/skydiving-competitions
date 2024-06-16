package by.grsu.skydiving.application.domain.exception.domain;

public class TeamWasNotFound extends DomainException {
    public TeamWasNotFound() {
        super("Запрашиваемая команда не была найдена.");
    }
}
