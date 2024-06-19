package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.trick.PenaltyReason;
import by.grsu.skydiving.application.domain.model.trick.PenaltyValues;
import by.grsu.skydiving.application.domain.model.trick.TrickAttemptsWithScore;
import by.grsu.skydiving.application.domain.model.trick.TrickType;
import java.util.Map;

public interface AddTrickAttemptsUseCase {
    TrickAttemptsWithScore addTrickAttempts(AddTrickAttemptToTrickSerieCommand command);

    record AddTrickAttemptToTrickSerieCommand(
        Long trickSerieId,
        PenaltyReason penaltyReason,
        Map<TrickType, PenaltyValues> trickAttempts
    ) {
    }
}
