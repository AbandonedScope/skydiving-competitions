package by.grsu.skydiving.application.domain.model.trickRefereeing;

import java.util.List;
import lombok.Builder;

@Builder
public record TrickRefereeing(
    Integer skydiverNumber,
    Integer serieNumber,
    Integer roundNumber,
    List<TrickSerie> trickSeries
) {
}
