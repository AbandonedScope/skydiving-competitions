package by.grsu.skydiving.application.domain.exception.business;

public class TrickSeieNotFoundException extends BusinessException {
    public TrickSeieNotFoundException() {
        super("Результаты судейства акробатики не были найдены.");
    }
}
