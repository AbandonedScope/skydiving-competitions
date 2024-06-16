package by.grsu.skydiving.application.domain.exception.business;

public class UpdateExternalSkydiverException extends BusinessException {
    public UpdateExternalSkydiverException(Long skydiverId) {
        super("Ошибка при обновлении внешнего парашютиста. Парашютист с id '%d' не является внешним."
            .formatted(skydiverId));
    }
}
