package by.grsu.skydiving.application.domain.model.trick;

import java.util.List;
import lombok.Builder;

@Builder
public record TrickSerieOfSkydiver(
    Integer memberNumber,
    Integer serieNumber,
    Integer roundNumber,
    Float totalScore,
    List<RefereeingResult> refereeingResults
) {
}
