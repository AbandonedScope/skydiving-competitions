package by.grsu.skydiving.application.domain.model.auth;

public class TokenVerificationException extends RuntimeException {
    public TokenVerificationException(Throwable cause) {
        super(cause);
    }
}
