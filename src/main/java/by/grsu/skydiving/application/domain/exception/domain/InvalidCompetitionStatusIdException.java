package by.grsu.skydiving.application.domain.exception.domain;

public class InvalidCompetitionStatusIdException extends DomainException {
    public InvalidCompetitionStatusIdException(int number) {
        super("Competition status for id '%d' does not exist.".formatted(number));
    }
}
