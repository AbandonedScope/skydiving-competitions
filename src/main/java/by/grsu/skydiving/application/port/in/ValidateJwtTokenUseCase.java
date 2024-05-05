package by.grsu.skydiving.application.port.in;

public interface ValidateJwtTokenUseCase {
    boolean isValid(String jwtToken);
}
