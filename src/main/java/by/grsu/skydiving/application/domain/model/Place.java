package by.grsu.skydiving.application.domain.model;

import by.grsu.skydiving.application.domain.exception.domain.ValidationException;

import java.util.HashMap;
import java.util.Map;

import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.*;

public record Place(
        String place
) {
    public Place{
        validate();
    }

    private void validate(){
        Map<String, String > errors = new HashMap<>();

        if (place == null || place.isBlank() || place.length() > 50) {
            errors.put(PLACE_INCORRECT_VALUE_KEY, PLACE_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE);
        }

        if (!errors.isEmpty()) {
            throw ValidationException.builder()
                    .errors(errors)
                    .build();
        }
    }
}
