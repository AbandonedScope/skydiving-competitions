package by.grsu.skydiving.application.domain.exception.business;

public class SkydiverNotFoundException extends BusinessException {
    public SkydiverNotFoundException(long skydiverId) {
        super("Skydiver with id '%d' was not found.".formatted(skydiverId));
    }
}
