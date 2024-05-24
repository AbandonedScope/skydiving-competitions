package by.grsu.skydiving.application.domain.model.competition;

import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.TEAM_NAME_INCORRECT_VALUE_KEY;
import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.TEAM_NAME_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE;
import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.TEAM_SKYDIVERS_INCORRECT_VALUE_KEY;
import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.TEAM_SKYDIVERS_IS_NULL_MESSAGE;

import by.grsu.skydiving.application.domain.exception.domain.TeamSizeLimitExceededException;
import by.grsu.skydiving.application.domain.exception.domain.ValidationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lombok.With;

public record Team(
        @With
        Long id,
        String name,
        @With
        Set<CompetitionMember> members
) {
    private static final int MAX_TEAM_SIZE = 5;

    public Team {
        validate(name, members);
    }

    public void addSkydiver(CompetitionMember skydiver) {
        if (members.size() >= MAX_TEAM_SIZE) {
            throw new TeamSizeLimitExceededException(MAX_TEAM_SIZE);
        }

        members.add(skydiver);
    }

    public void removeSkydiver(CompetitionMember skydiverToRemove) {
        members.remove(skydiverToRemove);
    }

    private void validate(String name, Set<CompetitionMember> skydivers) {
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
