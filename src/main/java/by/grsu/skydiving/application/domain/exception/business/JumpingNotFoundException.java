package by.grsu.skydiving.application.domain.exception.business;

public class JumpingNotFoundException extends BusinessException {
    public JumpingNotFoundException(long jumpingId) {
        super("Запись о прыжке с парашютом под id '%d' не была найдена."
            .formatted(jumpingId));
    }
}
