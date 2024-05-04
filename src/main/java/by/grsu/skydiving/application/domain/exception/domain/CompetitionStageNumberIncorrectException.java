package by.grsu.skydiving.application.domain.exception.domain;

public class CompetitionStageNumberIncorrectException extends DomainException {
    public CompetitionStageNumberIncorrectException(int invalidNumber, int possibleNumber) {
        super("Competition stage number incorrect exception. Actual number was '%d', but expected '%d'"
                .formatted(invalidNumber, possibleNumber));
    }
}
