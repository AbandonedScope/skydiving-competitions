package by.grsu.skydiving.application.domain.model.trickRefereeing;

import java.util.Map;
import lombok.Builder;

@Builder
public record TrickAttemptsIncome(
    Long trickSerieId,
    Map<TrickType, PenaltyValues> trickAttempts
) {
}
