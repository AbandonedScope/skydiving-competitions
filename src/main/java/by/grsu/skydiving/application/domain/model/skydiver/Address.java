package by.grsu.skydiving.application.domain.model.skydiver;

import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.ADDRESS_INCORRECT_VALUE_KEY;
import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.ADDRESS_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE;

import by.grsu.skydiving.application.domain.exception.domain.ValidationException;
import java.util.HashMap;
import java.util.Map;

public record Address(
    String address
) {
    public Address {
        validate(address);
    }

    private void validate(String address) {
        Map<String, String> errors = new HashMap<>();

        if (address == null || address.isBlank() || address.length() > 100) {
            errors.put(ADDRESS_INCORRECT_VALUE_KEY, ADDRESS_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE);
        }

        if (!errors.isEmpty()) {
            throw ValidationException.of(errors);
        }
    }
}
