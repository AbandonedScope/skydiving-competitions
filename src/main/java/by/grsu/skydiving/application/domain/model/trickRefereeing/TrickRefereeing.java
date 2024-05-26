package by.grsu.skydiving.application.domain.model.trickRefereeing;

import lombok.Builder;

import java.util.List;

@Builder
public record TrickRefereeing(
        Integer skydiverNumber,
        Integer serieNumber,
        Integer roundNumber,
        List<TrickSerie> trickSeries
) {
}
