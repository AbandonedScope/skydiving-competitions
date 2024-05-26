package by.grsu.skydiving.application.domain.model.trickRefereeing;

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
        Boolean isTimeSubmitted
) {
}
