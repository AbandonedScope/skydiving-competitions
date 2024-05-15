package by.grsu.skydiving.application.domain.model.skydiver;

import by.grsu.skydiving.application.domain.exception.domain.ValidationException;

import java.util.HashMap;
import java.util.Map;

import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.PHONE_NUMBER_DO_NOT_MATCH_PATTERN_OR_NULL_MESSAGE;
import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.PHONE_NUMBER_INCORRECT_VALUE_KEY;
import static by.grsu.skydiving.application.domain.model.consts.SkiDivingConstants.PHONE_NUMBER_REGEX_PATTERN;

public record PhoneNumber(
        String number
) {
    public PhoneNumber {
        validate(number);
    }

    private void validate(String number) {
        Map<String, String> errors = new HashMap<>();

        if (number == null || PHONE_NUMBER_REGEX_PATTERN.matcher(number).matches()) {
            errors.put(PHONE_NUMBER_INCORRECT_VALUE_KEY, PHONE_NUMBER_DO_NOT_MATCH_PATTERN_OR_NULL_MESSAGE);
        }

        if (!errors.isEmpty()) {
            throw ValidationException.of(errors);
        }
    }
}
