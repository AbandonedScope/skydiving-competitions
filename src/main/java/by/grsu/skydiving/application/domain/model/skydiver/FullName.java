package by.grsu.skydiving.application.domain.model.skydiver;


import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.FIRSTNAME_INCORRECT_VALUE_KEY;
import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.FIRSTNAME_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE;
import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.PATRONYMIC_INCORRECT_VALUE_KEY;
import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.PATRONYMIC_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE;
import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.SECONDNAME_INCORRECT_VALUE_KEY;
import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.SECONDNAME_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE;

import by.grsu.skydiving.application.domain.exception.domain.ValidationException;
import java.util.HashMap;
import java.util.Map;
import lombok.Builder;

@Builder
public record FullName(
    String firstName,
    String secondName,
    String patronymic
) {
    public FullName {
        validate(firstName, secondName, patronymic);
    }

    public String formatted() {
        return secondName.concat(" ".concat(firstName)).concat(" ".concat(patronymic));
    }

    private void validate(String firstName, String secondName, String patronymic) {
        Map<String, String> errors = new HashMap<>();

        if (isIncorrectPartOfFullName(firstName)) {
            errors.put(FIRSTNAME_INCORRECT_VALUE_KEY, FIRSTNAME_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE);
        }

        if (isIncorrectPartOfFullName(secondName)) {
            errors.put(SECONDNAME_INCORRECT_VALUE_KEY, SECONDNAME_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE);
        }

        if (isIncorrectPartOfFullName(patronymic)) {
            errors.put(PATRONYMIC_INCORRECT_VALUE_KEY, PATRONYMIC_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE);
        }

        if (!errors.isEmpty()) {
            throw ValidationException.of(errors);
        }
    }

    private boolean isIncorrectPartOfFullName(String partOfName) {
        return partOfName == null || partOfName.isBlank() || partOfName.length() > 50;
    }
}
