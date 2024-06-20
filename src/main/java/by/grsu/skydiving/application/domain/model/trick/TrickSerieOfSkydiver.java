package by.grsu.skydiving.application.domain.model.trick;

import java.util.List;
import lombok.Builder;

@Builder
public record TrickSerieOfSkydiver(
    Long competitionMemberId,
    Integer memberNumber,
    Integer serieNumber,
    Integer roundNumber,
    Float totalScore,
    List<RefereeingResult> refereeingResults
) {
    public static Float calculateTotalScore(List<RefereeingResult> refereeingResults) {
        if (refereeingResults.stream().anyMatch(refereeingResult -> refereeingResult.totalTime() == null)) {
            return null;
        }

        return (float) refereeingResults.stream()
            .mapToDouble(RefereeingResult::totalTime)
            .average()
            .getAsDouble();
    }

    public boolean isCompleted() {
        return refereeingResults.stream()
            .allMatch(RefereeingResult::isCompleted);
    }
}
