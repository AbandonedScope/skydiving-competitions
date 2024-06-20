package by.grsu.skydiving.application.domain.model.trick;

import lombok.Builder;

import java.util.Map;

@Builder
public record TrickAttemptsUpdate(
        Long trickSerieId,
        PenaltyReason penaltyReason,
        Map<TrickType, TrickAttemptForUpdate>  attempts
) {
}
