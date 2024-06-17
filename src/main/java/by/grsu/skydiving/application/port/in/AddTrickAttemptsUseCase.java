package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.trickRefereeing.PenaltyReason;
import by.grsu.skydiving.application.domain.model.trickRefereeing.PenaltyValues;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickAttemptsWithScore;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickType;

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
