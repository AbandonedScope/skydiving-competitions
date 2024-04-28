package by.grsu.skydiving.application.domain.model.jumping;

import by.grsu.skydiving.application.domain.exception.domain.ValidationException;

import java.util.HashMap;
import java.util.Map;

import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.*;

public record FlightCharacteristics(
        Float height,
        Float speed,
        Float timeDelayOfParachutOpening
) {
    public FlightCharacteristics{
        validate();
    }

    private void validate(){
        Map<String, String > errors = new HashMap<>();

        if (height == null || height < 0) {
            errors.put(FLIGHT_HEIGHT_INCORRECT_VALUE_KEY, FLIGHT_HEIGHT_NULL_OR_NEGATIVE_MESSAGE);
        }

        if (speed == null || speed < 0) {
            errors.put(SPEED_INCORRECT_VALUE_KEY, SPEED_NULL_OR_NEGATIVE_MESSAGE);
        }

        if (timeDelayOfParachutOpening == null || timeDelayOfParachutOpening < 0) {
            errors.put(TIME_DELAY_OF_PARACHUT_OPENING_INCORRECT_VALUE_KEY, TIME_DELAY_OF_PARACHUT_OPENING_NULL_OR_NEGATIVE_MESSAGE);
        }

        if (!errors.isEmpty()) {
            throw ValidationException.builder()
                    .errors(errors)
                    .build();
        }
    }
}
