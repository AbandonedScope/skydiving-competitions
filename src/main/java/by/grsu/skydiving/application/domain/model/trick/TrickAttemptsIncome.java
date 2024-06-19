package by.grsu.skydiving.application.domain.model.trick;

import java.util.Map;
import lombok.Builder;

@Builder
public record TrickAttemptsIncome(
    Long trickSerieId,
    PenaltyReason penaltyReason,
    Map<TrickType, PenaltyValues> trickAttempts
) {
}
