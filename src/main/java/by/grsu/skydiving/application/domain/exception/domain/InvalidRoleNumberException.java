package by.grsu.skydiving.application.domain.exception.domain;

public class InvalidRoleNumberException extends DomainException {
    public InvalidRoleNumberException(int roleNumber) {
        super("Role for number '%d' does not exist.".formatted(roleNumber));
    }
}
