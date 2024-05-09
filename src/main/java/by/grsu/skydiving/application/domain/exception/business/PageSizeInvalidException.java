package by.grsu.skydiving.application.domain.exception.business;

public class PageSizeInvalidException extends BusinessException {
    public PageSizeInvalidException(int pageSize) {
        super("Page size was '%d', but expected at least 1.".formatted(pageSize));
    }
}
