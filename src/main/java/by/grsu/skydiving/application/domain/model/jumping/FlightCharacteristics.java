package by.grsu.skydiving.application.domain.model.jumping;

import by.grsu.skydiving.application.domain.exception.domain.ValidationException;

import java.util.HashMap;
import java.util.Map;

import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.*;
import static by.grsu.skydiving.application.domain.model.consts.SkiDivingConstants.MIN_FLIGHT_HEIGHT;
import static by.grsu.skydiving.application.domain.model.consts.SkiDivingConstants.MIN_SPEED;

public record FlightCharacteristics(
        Float height,
        Float speed,
        Float timeDelayOfParachuteOpening
) {
    public FlightCharacteristics{
        validate(height, speed, timeDelayOfParachuteOpening);
    }

    private void validate(Float height, Float speed, Float timeDelayOfParachuteOpening){
        Map<String, String > errors = new HashMap<>();

        if (height == null || height < MIN_FLIGHT_HEIGHT) {
            errors.put(FLIGHT_HEIGHT_INCORRECT_VALUE_KEY, FLIGHT_HEIGHT_NULL_OR_NEGATIVE_MESSAGE);
        }

        if (speed == null || speed < MIN_SPEED) {
            errors.put(SPEED_INCORRECT_VALUE_KEY, SPEED_NULL_OR_NEGATIVE_MESSAGE);
        }

        if (timeDelayOfParachuteOpening == null || timeDelayOfParachuteOpening < 0) {
            errors.put(TIME_DELAY_OF_PARACHUT_OPENING_INCORRECT_VALUE_KEY, TIME_DELAY_OF_PARACHUTE_OPENING_NULL_OR_NEGATIVE_MESSAGE);
        }

        if (!errors.isEmpty()) {
            throw ValidationException.of(errors);
        }
    }
}
