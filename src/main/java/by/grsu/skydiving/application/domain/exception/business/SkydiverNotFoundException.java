package by.grsu.skydiving.application.domain.exception.business;

public class SkydiverNotFoundException extends BusinessException {
    public SkydiverNotFoundException(long skydiverId) {
        super("Парашютист с id '%d' не был найден.".formatted(skydiverId));
    }
}
