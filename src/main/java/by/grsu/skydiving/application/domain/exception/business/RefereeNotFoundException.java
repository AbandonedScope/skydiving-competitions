package by.grsu.skydiving.application.domain.exception.business;

public class RefereeNotFoundException extends BusinessException {
    public RefereeNotFoundException(Long id) {
        super("Судья с id '%d' не был найден".formatted(id));
    }
}
