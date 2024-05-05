package by.grsu.skydiving.application.domain.exception.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.Map;

@Builder
public class ValidationException extends DomainException {
    @Singular
    @Getter
    private final Map<String, String> errors;
    private static final String ERROR_MESSAGE = "Validation failed.";

    public ValidationException(Map<String, String> errors) {
        super(ERROR_MESSAGE);
        this.errors = errors;
    }

    public static ValidationException of(Map<String, String> errors) {
        return new ValidationException(errors);
    }
}
