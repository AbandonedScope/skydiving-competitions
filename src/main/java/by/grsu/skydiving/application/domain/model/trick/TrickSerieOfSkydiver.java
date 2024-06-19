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
        return refereeingResults.stream()
            .reduce((Float) null,
                (acc, refereeingResult) -> {
                    if (refereeingResult.totalTime() == null) {
                        return acc;
                    } else {
                        if (acc == null) {
                            return refereeingResult.totalTime();
                        } else {
                            acc = acc + refereeingResult.totalTime();
                        }

                        return acc >= 16.00f
                            ? 16.00f
                            : acc;
                    }
                },
                Float::sum
            );
    }
}
