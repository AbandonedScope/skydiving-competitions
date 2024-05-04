package by.grsu.skydiving.application.domain.model.competition;

import by.grsu.skydiving.application.domain.exception.domain.TeamSizeLimitExceededException;
import by.grsu.skydiving.application.domain.exception.domain.ValidationException;
import by.grsu.skydiving.application.domain.model.skydiver.Skydiver;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.*;

public record Team(
        String name,
        Set<Skydiver> skydivers
) {
    private static final int MAX_TEAM_SIZE = 5;

    public Team {
        validate();
    }

    public void addSkydiver(Skydiver skydiver) {
        if (skydivers.size() >= MAX_TEAM_SIZE) {
            throw new TeamSizeLimitExceededException(MAX_TEAM_SIZE);
        }

        skydivers.add(skydiver);
    }

    public void removeSkydiver(Skydiver skydiverToRemove) {
        skydivers.remove(skydiverToRemove);
    }

    private void validate() {
        Map<String, String> errors = new HashMap<>();

        if (name == null
                || name.isBlank()
                || name.length() > 40) {
            errors.put(TEAM_NAME_INCORRECT_VALUE_KEY, TEAM_NAME_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE);
        }

        if (skydivers == null) {
            errors.put(TEAM_SKYDIVERS_INCORRECT_VALUE_KEY, TEAM_SKYDIVERS_IS_NULL_MESSAGE);
        }

        if (!errors.isEmpty()) {
            throw ValidationException.of(errors);
        }
    }
}
