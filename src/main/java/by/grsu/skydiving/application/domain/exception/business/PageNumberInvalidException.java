package by.grsu.skydiving.application.domain.exception.business;

public class PageNumberInvalidException extends BusinessException {
    public PageNumberInvalidException(long pageNumber) {
        super("Page number was '%d', but expected at least 1.".formatted(pageNumber));
    }
}
