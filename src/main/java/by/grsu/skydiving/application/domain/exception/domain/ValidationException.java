package by.grsu.skydiving.application.domain.exception.domain;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Singular;

import java.util.Map;

@Builder
public class ValidationException extends DomainException {
    @Singular
    private final Map<String, String> errors;
    private static final String errorMessage = "Validation failed.";

    public ValidationException(Map<String, String> errors) {
        super(errorMessage);
        this.errors = errors;
    }

    public static ValidationException of(Map<String, String> errors) {
        return new ValidationException(errors);
    }
}
