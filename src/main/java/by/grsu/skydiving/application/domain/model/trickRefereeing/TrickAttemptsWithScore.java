package by.grsu.skydiving.application.domain.model.trickRefereeing;

import java.util.List;
import java.util.Map;
import lombok.Builder;

@Builder
public record TrickAttemptsWithScore(
    Float totalScore,
    Map<TrickType, TrickAttempt> trickAttempts
) {
    public static Float calculateTotalPenalty(List<TrickAttempt> trickAttemptList) {
        Float sum = trickAttemptList.stream()
            .map(TrickAttempt::calculateTrickPenalty)
            .reduce(0f, Float::sum);

        return sum > 16 ? 16 : sum;
    }
}
