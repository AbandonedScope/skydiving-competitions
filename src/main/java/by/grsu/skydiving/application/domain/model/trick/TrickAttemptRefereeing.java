package by.grsu.skydiving.application.domain.model.trick;

import java.util.Map;
import lombok.Builder;

@Builder
public record TrickAttemptRefereeing(
    Long id,
    Long trickSerieId,
    TrickType trickType,
    Map<PenaltyType, PenaltyMetrics> penalties
) {
    public Float calculateTrickPenalty() {
        return penalties.values().stream()
            .map(PenaltyMetrics::penaltyValueTime)
            .reduce(0f, Float::sum);
    }
}
