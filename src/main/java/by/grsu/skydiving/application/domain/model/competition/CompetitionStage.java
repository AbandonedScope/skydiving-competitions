package by.grsu.skydiving.application.domain.model.competition;

import by.grsu.skydiving.application.domain.exception.domain.ValidationException;
import by.grsu.skydiving.application.domain.model.JudgeCollegium;
import by.grsu.skydiving.application.domain.model.MainJudgeCollegium;

import java.util.HashMap;
import java.util.Map;

import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.*;

public record CompetitionStage(
        Long id,
        Integer number,
        MainJudgeCollegium mainJudgeCollegium,
        JudgeCollegium judgeCollegium
) {
    public CompetitionStage {
        validate();
    }

    public void validate() {
        Map<String, String> errors = new HashMap<>();

        if (number == null
                || number < 1
        ) {
            errors.put(COMPETITION_STAGE_NUMBER_INCORRECT_VALUE_KEY, COMPETITION_STAGE_NUMBER_NULL_OR_INVALID_MESSAGE);
        }

        if (mainJudgeCollegium == null) {
            errors.put(COMPETITION_STAGE_MAIN_JUDGE_COLLEGIUM_VALUE_KEY, COMPETITION_STAGE_MAIN_JUDGE_COLLEGIUM_NULL_MESSAGE);
        }

        if (judgeCollegium == null) {
            errors.put(COMPETITION_STAGE_JUDGE_COLLEGIUM_VALUE_KEY, COMPETITION_STAGE_JUDGE_COLLEGIUM_NULL_MESSAGE);
        }

        if (!errors.isEmpty()) {
            throw ValidationException.of(errors);
        }
    }
}
