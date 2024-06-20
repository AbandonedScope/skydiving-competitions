package by.grsu.skydiving.application.domain.model.trick;

import lombok.Builder;

@Builder
public record TrickAttemptForUpdate(
        Long id,
        Integer arrowPenalty,
        Integer dPenalty,
        Integer sPenalty,
        Integer minusPenalty,
        Integer plusMinusPenalty
) {
    public Float calculateTotalPenaltyForTrick(){
        return getPenaltyTimeFromPenalty(PenaltyType.D_PENALTY, dPenalty) +
                getPenaltyTimeFromPenalty(PenaltyType.S_PENALTY, sPenalty) +
                getPenaltyTimeFromPenalty(PenaltyType.ARROW_PENALTY, arrowPenalty) +
                getPenaltyTimeFromPenalty(PenaltyType.MINUS_PENALTY, minusPenalty) +
                getPenaltyTimeFromPenalty(PenaltyType.PLUS_MINUS_PENALTY, plusMinusPenalty);
    }

    public Float getPenaltyTimeFromPenalty(PenaltyType penaltyType, Integer penaltyValue) {
        return switch (penaltyType) {
            case D_PENALTY,
                 S_PENALTY,
                 PLUS_MINUS_PENALTY -> getBluePenaltyDegreeEquivalentInSeconds(penaltyValue);
            case ARROW_PENALTY,
                 MINUS_PENALTY -> getGreenPenaltyDegreeEquivalentInSeconds(penaltyValue);
        };
    }

    private Float getBluePenaltyDegreeEquivalentInSeconds(Integer penaltyValue) {
        return switch (penaltyValue) {
            case 40 -> 0.4f;
            case 70 -> 1.5f;
            case 100 -> 16f;
            default -> 0f;
        };
    }

    private Float getGreenPenaltyDegreeEquivalentInSeconds(Integer penaltyValue) {
        if (penaltyValue > 90) {
            return 16f;
        }

        return penaltyValue / 50f;
    }
}
