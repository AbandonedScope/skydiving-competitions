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
    public static Float calculateTotalScore(List<RefereeingResult> refereeingResults) {
        if (refereeingResults.stream().anyMatch(refereeingResult -> refereeingResult.totalTime() == null)) {
            return null;
        }

        return refereeingResults.stream().reduce(0.0f,
                (acc, refereeingResult) -> {
                    acc = acc + refereeingResult.totalTime();

                    return acc >= 16.00f
                            ? 16.00f
                            : acc;
                },
                Float::sum
            );
    }
}
