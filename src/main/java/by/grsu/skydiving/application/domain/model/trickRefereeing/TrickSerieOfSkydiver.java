package by.grsu.skydiving.application.domain.model.trickRefereeing;

import lombok.Builder;

@Builder
public record TrickSerieOfSkydiver(
        Integer skydiverNumber,
        Integer serieNumber,
        Integer roundNumber,
        Float score,
        TrickSerieExtended trickSerieWithPenalties
) {

}
