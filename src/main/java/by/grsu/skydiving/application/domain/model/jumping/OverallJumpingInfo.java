package by.grsu.skydiving.application.domain.model.jumping;

import by.grsu.skydiving.application.domain.exception.domain.ValidationException;

import java.util.HashMap;
import java.util.Map;

import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.*;

public record OverallJumpingInfo(
        Short nextJumpNumberInCompetition,
        Short nextJumpNumberDuringYear,
        Short jumpNumberBillable
) {
    public OverallJumpingInfo {
        validate(nextJumpNumberInCompetition, nextJumpNumberDuringYear, jumpNumberBillable);
    }

    private void validate(Short nextJumpNumberInCompetition, Short nextJumpNumberDuringYear, Short jumpNumberBillable) {
        Map<String, String> errors = new HashMap<>();

        if (nextJumpNumberDuringYear == null || nextJumpNumberDuringYear < 0) {
            errors.put(NEXT_JUMP_NUMBER_DURING_YEAR_INCORRECT_VALUE_KEY, NEXT_JUMP_NUMBER_DURING_YEAR_IS_NULL_OR_NEGATIVE_MESSAGE);
        }

        if (nextJumpNumberInCompetition == null || nextJumpNumberInCompetition < 0) {
            errors.put(NEXT_JUMP_NUMBER_IN_COMPETITION_UNIT_INCORRECT_VALUE_KEY, NEXT_JUMP_NUMBER_IN_COMPETITION_IS_NULL_OR_NEGATIVE_MESSAGE);
        }

        if (jumpNumberBillable == null || jumpNumberBillable < 0) {
            errors.put(JUMP_NUMBER_BILLABLE_INCORRECT_VALUE_KEY, JUMP_NUMBER_BILLABLE_IS_NULL_OR_NEGATIVE_MESSAGE);
        }

        if (!errors.isEmpty()) {
            throw ValidationException.of(errors);
        }
    }
}
