package by.grsu.skydiving.application.domain.exception.business;

public class RefereesNotFoundException extends BusinessException {
    public RefereesNotFoundException() {
        super("Referees were not found.");
    }
}
