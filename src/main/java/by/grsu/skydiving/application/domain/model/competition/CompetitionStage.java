package by.grsu.skydiving.application.domain.model.competition;

import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.COMPETITION_STAGE_JUDGE_COLLEGIUM_NULL_MESSAGE;
import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.COMPETITION_STAGE_JUDGE_COLLEGIUM_VALUE_KEY;
import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.COMPETITION_STAGE_MAIN_JUDGE_COLLEGIUM_NULL_MESSAGE;
import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.COMPETITION_STAGE_MAIN_JUDGE_COLLEGIUM_VALUE_KEY;
import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.COMPETITION_STAGE_NUMBER_INCORRECT_VALUE_KEY;
import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.COMPETITION_STAGE_NUMBER_NULL_OR_INVALID_MESSAGE;

import by.grsu.skydiving.application.domain.exception.domain.ValidationException;
import by.grsu.skydiving.application.domain.model.RefereeCollegium;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import lombok.Builder;
import lombok.With;

@Builder
public record CompetitionStage(
    Long id,
    Integer number,
    @With
    RefereeCollegium mainCollegium,
    @With
    RefereeCollegium collegium
) {
    public CompetitionStage {
        validate(number, mainCollegium, collegium);
    }

    public void validate(Integer number, RefereeCollegium mainRefereeCollegium, RefereeCollegium refereeCollegium) {
        Map<String, String> errors = new HashMap<>();

        if (number == null || number < 1) {
            errors.put(COMPETITION_STAGE_NUMBER_INCORRECT_VALUE_KEY, COMPETITION_STAGE_NUMBER_NULL_OR_INVALID_MESSAGE);
        }

        if (mainRefereeCollegium == null) {
            errors.put(COMPETITION_STAGE_MAIN_JUDGE_COLLEGIUM_VALUE_KEY,
                COMPETITION_STAGE_MAIN_JUDGE_COLLEGIUM_NULL_MESSAGE);
        }

        if (refereeCollegium == null) {
            errors.put(COMPETITION_STAGE_JUDGE_COLLEGIUM_VALUE_KEY, COMPETITION_STAGE_JUDGE_COLLEGIUM_NULL_MESSAGE);
        }

        if (!errors.isEmpty()) {
            throw ValidationException.of(errors);
        }
    }

    public static class CompetitionStageBuilder {
        private RefereeCollegium mainCollegium = new RefereeCollegium(HashSet.newHashSet(5));
        private RefereeCollegium collegium = new RefereeCollegium(HashSet.newHashSet(5));
    }
}
