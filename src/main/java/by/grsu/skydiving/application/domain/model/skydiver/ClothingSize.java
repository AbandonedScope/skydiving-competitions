package by.grsu.skydiving.application.domain.model.skydiver;

import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.JACKET_SIZE_INCORRECT_VALUE_KEY;
import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.JACKET_SIZE_INCORRECT_VALUE_MESSAGE;
import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.PANTS_SIZE_INCORRECT_VALUE_KEY;
import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.PANTS_SIZE_INCORRECT_VALUE_MESSAGE;
import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.SHOE_SIZE_INCORRECT_VALUE_KEY;
import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.SHOE_SIZE_INCORRECT_VALUE_MESSAGE;
import static by.grsu.skydiving.application.domain.model.consts.SkiDivingConstants.MIN_SKYDIVER_JACKETSIZE;
import static by.grsu.skydiving.application.domain.model.consts.SkiDivingConstants.MIN_SKYDIVER_PANTSSIZE;
import static by.grsu.skydiving.application.domain.model.consts.SkiDivingConstants.MIN_SKYDIVER_SHOESIZE;

import by.grsu.skydiving.application.domain.exception.domain.ValidationException;
import java.util.HashMap;
import java.util.Map;

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
