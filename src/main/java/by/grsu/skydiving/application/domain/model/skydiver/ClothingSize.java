package by.grsu.skydiving.application.domain.model.skydiver;

import by.grsu.skydiving.application.domain.exception.domain.ValidationException;

import java.util.HashMap;
import java.util.Map;

import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.*;
import static by.grsu.skydiving.application.domain.model.consts.SkiDivingConstants.*;

public record ClothingSize(
        int shoeSize,
        int jacketSize,
        int pantsSize
) {
    public ClothingSize {
        validate(shoeSize, jacketSize, pantsSize);
    }

    private void validate(int shoeSize, int jacketSize, int pantsSize) {
        Map<String, String> errors = new HashMap<>();

        if (shoeSize < MIN_SKYDIVER_SHOESIZE) {
            errors.put(SHOE_SIZE_INCORRECT_VALUE_KEY, SHOE_SIZE_INCORRECT_VALUE_MESSAGE);
        }

        if (jacketSize < MIN_SKYDIVER_JACKETSIZE) {
            errors.put(JACKET_SIZE_INCORRECT_VALUE_KEY, JACKET_SIZE_INCORRECT_VALUE_MESSAGE);
        }

        if (pantsSize < MIN_SKYDIVER_PANTSSIZE) {
            errors.put(PANTS_SIZE_INCORRECT_VALUE_KEY, PANTS_SIZE_INCORRECT_VALUE_MESSAGE);
        }

        if (!errors.isEmpty()) {
            throw ValidationException.of(errors);
        }
    }
}
