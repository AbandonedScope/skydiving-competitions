package by.grsu.skydiving.application.domain.exception.business;

public class TokenVerificationException extends BusinessException {
    public TokenVerificationException(Throwable cause) {
        super(cause.getMessage());
    }
}
