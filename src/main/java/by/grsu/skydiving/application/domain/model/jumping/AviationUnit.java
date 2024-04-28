package by.grsu.skydiving.application.domain.model.jumping;

import by.grsu.skydiving.application.domain.exception.domain.ValidationException;

import java.util.HashMap;
import java.util.Map;

import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.*;

public record AviationUnit(
        String aviationUnitName
) {
    public AviationUnit{
        validate();
    }

    private void validate(){
        Map<String, String > errors = new HashMap<>();

        if (aviationUnitName == null || aviationUnitName.isBlank() || aviationUnitName.length() > 50) {
            errors.put(AVIATION_UNIT_INCORRECT_VALUE_KEY, AVIATION_UNIT_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE);
        }

        if (!errors.isEmpty()) {
            throw ValidationException.builder()
                    .errors(errors)
                    .build();
        }
    }
}
