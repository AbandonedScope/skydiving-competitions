package by.grsu.skydiving.application.domain.exception.business;

public class RefereeNotFoundException extends BusinessException {
    public RefereeNotFoundException(Long id) {
        super("Referee with id '%d' was not found".formatted(id));
    }
}
