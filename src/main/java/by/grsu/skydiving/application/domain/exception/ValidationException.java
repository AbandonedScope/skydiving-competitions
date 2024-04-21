package by.grsu.skydiving.application.domain.exception;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Singular;

import java.util.Map;

@Builder
@RequiredArgsConstructor
public class ValidationException extends RuntimeException {
    @Singular
    private final Map<String, String> errors;
}
