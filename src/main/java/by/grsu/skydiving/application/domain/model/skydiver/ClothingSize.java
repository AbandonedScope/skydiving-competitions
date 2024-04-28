package by.grsu.skydiving.application.domain.model.skydiver;

import by.grsu.skydiving.application.domain.exception.domain.ValidationException;

import java.util.HashMap;
import java.util.Map;

import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.*;

public record ClothingSize(
        int shoeSize,
        int jacketSize,
        int pantsSize
) {
    public ClothingSize {
        validate();
    }

    private void validate() {
        Map<String, String > errors = new HashMap<>();

        if (shoeSize < 0) {
            errors.put(SHOE_SIZE_INCORRECT_VALUE_KEY, SHOE_SIZE_INCORRECT_VALUE_MESSAGE);
        }

        if (jacketSize < 0) {
            errors.put(JACKET_SIZE_INCORRECT_VALUE_KEY, JACKET_SIZE_INCORRECT_VALUE_MESSAGE);
        }

        if (pantsSize < 0) {
            errors.put(PANTS_SIZE_INCORRECT_VALUE_KEY, PANTS_SIZE_INCORRECT_VALUE_MESSAGE);
        }

        if (!errors.isEmpty()) {
            throw ValidationException.builder()
                    .errors(errors)
                    .build();
        }
    }
}
