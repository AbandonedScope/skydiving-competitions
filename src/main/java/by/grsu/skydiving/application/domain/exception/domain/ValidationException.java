package by.grsu.skydiving.application.domain.exception.domain;

import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

@Builder
public class ValidationException extends DomainException {
    private static final String ERROR_MESSAGE = "Validation failed.";
    @Singular
    @Getter
    private final Map<String, String> errors;

    public ValidationException(Map<String, String> errors) {
        super(ERROR_MESSAGE);
        this.errors = errors;
    }

    public static ValidationException of(Map<String, String> errors) {
        return new ValidationException(errors);
    }
}
