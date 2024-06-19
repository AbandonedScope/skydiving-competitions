package by.grsu.skydiving.application.domain.model.trick;

import lombok.Builder;

@Builder
public record TrickSerie(
    Long id,
    Long refereeId,
    Long refereeNumber,
    Integer score,
    Integer serieNumber,
    Integer roundNumber,
    Float timeWithoutPenalty,
    Float totalPenalty,
    PenaltyReason penaltyReason,
    Boolean isTimeSubmitted
) {
}
