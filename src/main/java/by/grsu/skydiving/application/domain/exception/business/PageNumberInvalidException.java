package by.grsu.skydiving.application.domain.exception.business;

public class PageNumberInvalidException extends BusinessException {
    public PageNumberInvalidException(long pageNumber) {
        super("Номер страницы был задан как '%d', но минимальное значение равно 1.".formatted(pageNumber));
    }
}
