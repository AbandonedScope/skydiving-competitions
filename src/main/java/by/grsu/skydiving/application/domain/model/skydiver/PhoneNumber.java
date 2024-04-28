package by.grsu.skydiving.application.domain.model.skydiver;

import by.grsu.skydiving.application.domain.exception.domain.ValidationException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.*;
import static by.grsu.skydiving.application.domain.model.consts.SkiDivingConstants.*;

public record PhoneNumber(
        String number
) {
    public PhoneNumber {
       validate();
    }

    private void validate(){
        Map<String, String > errors = new HashMap<>();

        if (number == null || REGEX_PATTERN.matcher(number).matches()) {
            errors.put(PHONE_NUMBER_INCORRECT_VALUE_KEY, PHONE_NUMBER_DO_NOT_MATCH_PATTERN_OR_NULL_MESSAGE);
        }

        if (!errors.isEmpty()) {
            throw ValidationException.builder()
                    .errors(errors)
                    .build();
        }
    }
}
