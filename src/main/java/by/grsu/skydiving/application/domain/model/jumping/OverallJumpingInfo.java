package by.grsu.skydiving.application.domain.model.jumping;

import by.grsu.skydiving.application.domain.exception.domain.ValidationException;

import java.util.HashMap;
import java.util.Map;

import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.*;
import static by.grsu.skydiving.application.domain.exception.ErrorMessagesConstants.TIME_DELAY_OF_PARACHUT_OPENING_NULL_OR_NEGATIVE_MESSAGE;

public record OverallJumpingInfo(
        Short nextJumpNumberInCompetition,
        Short nextJumpNumberDuringYear,
        Short jumpNumberBillable) {
    public OverallJumpingInfo{
        validate();
    }

    private void validate(){
        Map<String, String > errors = new HashMap<>();

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
            throw ValidationException.builder()
                    .errors(errors)
                    .build();
        }
    }
}
