package by.grsu.skydiving.application.domain.model.competition;

import by.grsu.skydiving.application.domain.exception.domain.ValidationException;

import java.util.HashMap;
import java.util.Map;

import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.*;

public record Team (
        String name
){
    public Team{
        validate();
    }

    private void validate(){
        Map<String, String > errors = new HashMap<>();

        if (name == null
                || name.isBlank()
                || name.length() > 40) {
            errors.put(TEAM_NAME_INCORRECT_VALUE_KEY, TEAM_NAME_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE);
        }

        if (!errors.isEmpty()) {
            throw ValidationException.of(errors);
        }
    }
}
