package by.grsu.skydiving.application.domain.model.skydiver;


import by.grsu.skydiving.application.domain.exception.domain.ValidationException;

import java.util.HashMap;
import java.util.Map;

import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.*;

public record FullName(
        String firstName,
        String secondName,
        String patronymic
) {
    public FullName {
        validate();
    }

    private void validate() {
        Map<String, String > errors = new HashMap<>();

        if (isCorrectPartOfFullName(firstName)) {
            errors.put(FIRSTNAME_INCORRECT_VALUE_KEY, FIRSTNAME_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE);
        }

        if (isCorrectPartOfFullName(secondName)) {
            errors.put(SECONDNAME_INCORRECT_VALUE_KEY, SECONDNAME_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE);
        }

        if (isCorrectPartOfFullName(patronymic)) {
            errors.put(PATRONYMIC_INCORRECT_VALUE_KEY, PATRONYMIC_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE);
        }

        if (!errors.isEmpty()) {
            throw ValidationException.builder()
                    .errors(errors)
                    .build();
        }
    }

    private boolean isCorrectPartOfFullName(String partOfName){
        return partOfName == null || partOfName.isBlank() || partOfName.length() > 50;
    }
}
