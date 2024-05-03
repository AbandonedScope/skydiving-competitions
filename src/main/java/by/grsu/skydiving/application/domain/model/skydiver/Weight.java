package by.grsu.skydiving.application.domain.model.skydiver;

import by.grsu.skydiving.application.domain.exception.domain.ValidationException;

import java.util.HashMap;
import java.util.Map;

import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.*;
import static by.grsu.skydiving.application.domain.model.consts.SkiDivingConstants.*;

public record Weight(
        Float weight
) {
    public Weight {
        validate();
    }

    private void validate(){
        Map<String, String > errors = new HashMap<>();

        if (weight == null || weight < MIN_SKYDIVER_WEIGHT || weight > MAX_SKYDIVER_WEIGHT) {
            errors.put(WEIGHT_INCORRECT_VALUE_KEY, WEIGHT_OUT_OF_RANGE_OR_NULL_MESSAGE);
        }

        if (!errors.isEmpty()) {
            throw ValidationException.of(errors);
        }
    }
}
