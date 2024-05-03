package by.grsu.skydiving.application.domain.exception.business;

public class BusinessException extends RuntimeException{
    public BusinessException(String errorMessage) {
        super(errorMessage);
    }
}
