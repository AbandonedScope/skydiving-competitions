package by.grsu.skydiving.application.domain.exception.business;

public class PageSizeInvalidException extends BusinessException {
    public PageSizeInvalidException(int pageSize) {
        super("Был запрошен следующий размер страницы '%d', но минимальным размером страницы является 1.".formatted(
            pageSize));
    }
}
