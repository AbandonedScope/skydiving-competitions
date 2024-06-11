package by.grsu.skydiving.application.domain.exception.domain;

public class CompetitionAlreadyHasCollegiumException extends DomainException {
    public CompetitionAlreadyHasCollegiumException() {
        super("Соревнование уже имеет судейскую коллегию.");
    }
}
