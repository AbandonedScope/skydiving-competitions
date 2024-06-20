package by.grsu.skydiving.application.domain.model.trick;

import lombok.Builder;
import lombok.With;

import java.util.Map;

@Builder
public record TrickAttemptsUpdate(
        Long trickSerieId,
        PenaltyReason penaltyReason,
        @With
        Float totalScore,
        Map<TrickType, TrickAttemptForUpdate>  attempts
) {
    public Float calculateTotalPenalty(PenaltyReason penaltyReason) {
        if (penaltyReason != PenaltyReason.NP) {
            return  16f;
        }

        Float sum = attempts.values().stream()
                .map(TrickAttemptForUpdate::calculateTotalPenaltyForTrick)
                .reduce(0f, Float::sum);

        return sum > 16 ? 16 : sum;
    }
}
