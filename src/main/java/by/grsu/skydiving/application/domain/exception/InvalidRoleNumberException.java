package by.grsu.skydiving.application.domain.exception;

public class InvalidRoleNumberException extends RuntimeException {
    public InvalidRoleNumberException(int roleNumber) {
        super("Role for number '%d' does not exist.".formatted(roleNumber));
    }
}
