package by.grsu.skydiving.application.domain.exception.domain;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Singular;

import java.util.Map;

@Builder
@RequiredArgsConstructor
public class ValidationException extends DomainException {
    @Singular
    private final Map<String, String> errors;
}
