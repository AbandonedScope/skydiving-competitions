package by.grsu.skydiving.application.domain.model.skydiver;

import by.grsu.skydiving.application.domain.exception.domain.ValidationException;

import java.util.HashMap;
import java.util.Map;

import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.*;
import static by.grsu.skydiving.application.domain.model.consts.SkiDivingConstants.*;

public record Height(
        Float height
) {
    public Height {
       validate();
    }

    private void validate(){
        Map<String, String > errors = new HashMap<>();

        if (height == null || height < MIN_SKYDIVER_HEIGHT || height > MAX_SKYDIVER_HEIGHT) {
            errors.put(HEIGHT_INCORRECT_VALUE_KEY, HEIGHT_OUT_OF_RANGE_OR_NULL_MESSAGE);
        }

        if (!errors.isEmpty()) {
            throw ValidationException.of(errors);
        }
    }
}
