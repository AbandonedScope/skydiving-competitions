package by.grsu.skydiving.application.domain.model.auth;

public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException() {
        super("Jwt auth token was expired.");
    }
}
