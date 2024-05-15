package by.grsu.skydiving.application.domain.exception.domain;

public class InvalidCompetitionStatusNumberException extends DomainException {
    public InvalidCompetitionStatusNumberException(int number) {
        super("Competition status for number '%d' does not exist.".formatted(number));
    }
}
