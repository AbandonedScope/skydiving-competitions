package by.grsu.skydiving.application.domain.model.skydiver;

import by.grsu.skydiving.application.domain.exception.domain.ValidationException;

import java.util.HashMap;
import java.util.Map;

import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.*;
import static by.grsu.skydiving.application.domain.model.consts.SkiDivingConstants.MAX_HEIGHT;
import static by.grsu.skydiving.application.domain.model.consts.SkiDivingConstants.MIN_HEIGHT;

public record Height(
        Float height
) {
    public Height {
       validate();
    }

    private void validate(){
        Map<String, String > errors = new HashMap<>();

        if (height == null || height < MIN_HEIGHT || height > MAX_HEIGHT) {
            errors.put(HEIGHT_INCORRECT_VALUE_KEY, HEIGHT_OUT_OF_RANGE_OR_NULL_MESSAGE);
        }

        if (!errors.isEmpty()) {
            throw ValidationException.builder()
                    .errors(errors)
                    .build();
        }
    }
}
