package by.grsu.skydiving.application.port.in;

public interface ExtractLoginFromJwtTokenUseCase {
    String extractLogin(String token);
}
