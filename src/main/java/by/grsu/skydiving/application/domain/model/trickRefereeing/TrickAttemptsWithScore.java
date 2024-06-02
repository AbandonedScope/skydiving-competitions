package by.grsu.skydiving.application.domain.model.trickRefereeing;

import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
public record TrickAttemptsWithScore(
        Float totalScore,
        Map<TrickType, TrickAttempt> trickAttempts
) {
    public static Float calculateTotalPenalty(List<TrickAttempt> trickAttemptList){
        return trickAttemptList.stream()
                .map(TrickAttempt::calculateTrickPenalty)
                .reduce(0f, Float::sum);
    }
}
