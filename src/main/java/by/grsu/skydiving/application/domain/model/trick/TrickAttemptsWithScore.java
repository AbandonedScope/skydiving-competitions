package by.grsu.skydiving.application.domain.model.trick;

import java.util.List;
import java.util.Map;
import lombok.Builder;

@Builder
public record TrickAttemptsWithScore(
    PenaltyReason penaltyReason,
    Float totalScore,
    Map<TrickType, TrickAttempt> trickAttempts
) {
    public static Float calculateTotalPenalty(PenaltyReason penaltyReason, List<TrickAttempt> trickAttemptList) {
        if (penaltyReason != PenaltyReason.NP) {
            return  16f;
        }

        Float sum = trickAttemptList.stream()
            .map(TrickAttempt::calculateTrickPenalty)
            .reduce(0f, Float::sum);

        return sum > 16 ? 16 : sum;
    }
}
