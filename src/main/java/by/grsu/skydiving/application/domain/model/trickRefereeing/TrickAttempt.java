package by.grsu.skydiving.application.domain.model.trickRefereeing;

import java.util.List;
import lombok.Builder;

@Builder
public record TrickAttempt(
    Long id,
    Long trickSerieId,
    TrickType trickType,
    List<PenaltyMetrics> penalties
) {
    public Float calculateTrickPenalty() {
        return penalties.stream()
            .map(PenaltyMetrics::penaltyValueTime)
            .reduce(0f, Float::sum);
    }
}
