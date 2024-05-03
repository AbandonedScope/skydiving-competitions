package by.grsu.skydiving.application.domain.model.skydiver;

import by.grsu.skydiving.application.domain.exception.domain.ValidationException;

import java.util.HashMap;
import java.util.Map;

import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.*;

public record Address(
        String address
) {
    public Address{
        validate();
    }

    private void validate(){
        Map<String, String > errors = new HashMap<>();

        if (address == null || address.isBlank() || address.length() > 100) {
            errors.put(ADDRESS_INCORRECT_VALUE_KEY, ADDRESS_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE);
        }

        if (!errors.isEmpty()) {
            throw ValidationException.of(errors);
        }
    }
}
