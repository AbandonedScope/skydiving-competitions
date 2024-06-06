package by.grsu.skydiving.application.domain.model.trickRefereeing;

import lombok.Builder;
import lombok.With;

@Builder
public record PenaltyMetrics(
    PenaltyType penaltyType,
    Integer penaltyValue,
    @With
    Float penaltyValueTime
) {
    public Float getPenaltyTimeFromPenalty() {
        return switch (penaltyType) {
            case D_PENALTY,
                 S_PENALTY,
                 PLUS_MINUS_PENALTY -> getBluePenaltyDegreeEquivalentInSeconds();
            case ARROW_PENALTY,
                 MINUS_PENALTY -> getGreenPenaltyDegreeEquivalentInSeconds();
        };
    }

    private Float getBluePenaltyDegreeEquivalentInSeconds() {
        return switch (penaltyValue) {
            case 40 -> 0.4f;
            case 70 -> 0.7f;
            case 100 -> 16f;
            default -> 0f;
        };
    }

    private Float getGreenPenaltyDegreeEquivalentInSeconds() {
        if (penaltyValue > 90) {
            return 16f;
        }

        return penaltyValue / 5f;
    }
}
