package by.grsu.skydiving.application.domain.model.trick;

import lombok.Builder;

@Builder
public record TrickSerieExtended(
        Long id,
        Long refereeId,
        Long refereeNumber,
        Float timeWithoutPenalty,
        Float totalPenalty,
        Boolean isTimeSubmitted,
        PenaltyReason penaltyReason,
        TrickAttemptsWithScore trickAttemptsWithScore
){
}
