package by.grsu.skydiving.application.domain.exception.domain;

public class DomainException extends RuntimeException{
    public DomainException(String errorMessage) {
        super(errorMessage);
    }
}
