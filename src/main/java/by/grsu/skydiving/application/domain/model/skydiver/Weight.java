package by.grsu.skydiving.application.domain.model.skydiver;

import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.WEIGHT_INCORRECT_VALUE_KEY;
import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.WEIGHT_OUT_OF_RANGE_OR_NULL_MESSAGE;
import static by.grsu.skydiving.application.domain.model.consts.SkiDivingConstants.MAX_SKYDIVER_WEIGHT;
import static by.grsu.skydiving.application.domain.model.consts.SkiDivingConstants.MIN_SKYDIVER_WEIGHT;

import by.grsu.skydiving.application.domain.exception.domain.ValidationException;
import java.util.HashMap;
import java.util.Map;

public record Weight(
    Float weight
) {
    public Weight {
        validate(weight);
    }

    private void validate(Float weight) {
        Map<String, String> errors = new HashMap<>();

        if (weight == null || weight < MIN_SKYDIVER_WEIGHT || weight > MAX_SKYDIVER_WEIGHT) {
            errors.put(WEIGHT_INCORRECT_VALUE_KEY, WEIGHT_OUT_OF_RANGE_OR_NULL_MESSAGE);
        }

        if (!errors.isEmpty()) {
            throw ValidationException.of(errors);
        }
    }
}
