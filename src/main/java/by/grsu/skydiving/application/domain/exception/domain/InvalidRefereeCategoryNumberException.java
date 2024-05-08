package by.grsu.skydiving.application.domain.exception.domain;

public class InvalidRefereeCategoryNumberException extends DomainException {
    public InvalidRefereeCategoryNumberException(int categoryNumber) {
        super("Referee category for number '%d' does not exist.".formatted(categoryNumber));
    }
}